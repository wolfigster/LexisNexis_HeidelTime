package de.wolfig;

import de.wolfig.response.document.BodyText;
import de.wolfig.response.document.Entry;
import de.wolfig.response.list.DocumentList;
import de.wolfig.files.Configuration;
import de.wolfig.files.Reader;
import de.wolfig.files.Writer;
import de.wolfig.lexisnexis.Requester;
import de.wolfig.response.list.Value;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Worker {

    private final File overviewFile = new File("./overview.csv");
    private final File listFile = new File("./list.txt");
    private final File filesDirectory = new File("./files");
    private final File xmlDirectory = new File(filesDirectory.getPath() + "/xml");
    private final File txtDirectory = new File(filesDirectory.getPath() + "/txt");
    private final File csvDirectory = new File(filesDirectory.getPath() + "/csv");
    private Requester requester = null;
    private Reader reader = null;
    private Writer writer = null;

    public Worker() {
        requester = new Requester(Configuration.getAccessToken());
        reader = new Reader();
        writer = new Writer(listFile, true);
        createDirectoriesIfNecessary();
    }

    public void createDirectoriesIfNecessary() {
        if(!filesDirectory.exists()) filesDirectory.mkdir();
        if(!xmlDirectory.exists()) xmlDirectory.mkdir();
        if(!txtDirectory.exists()) txtDirectory.mkdir();
        if(!csvDirectory.exists()) csvDirectory.mkdir();
    }

    public void stop() {
        writer.closeWriter();
    }

    public void initializeList() {
        if(overviewFile.exists()) {
            overviewFile.delete();
        }
        writer.changeWriterFile(overviewFile);
        writer.writeToFile("URN;Title;Date;WordLength;Document\n");
        for(String listItem : reader.readFileLineByLine(listFile)) {
            writer.writeToFile(getValuesAsStringFromListItem(listItem));
        }
        requestAllDocuments();
    }

    private void requestAllDocuments() {
        String[] response = null;
        String responseString = null;
        for(String responseItem : reader.readFileLineByLine(overviewFile)) {
            response = responseItem.split(";");
            if(response[0].equals("URN")) continue;
            responseString = requester.request("https://services-api.lexisnexis.com/v1/" + response[4]);
            try {
                TimeUnit.MILLISECONDS.sleep(1); // test for maximal responses
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.changeWriterSettings(xmlDirectory.getPath() + "/" + response[1] + ".xml", false);
            writer.writeToFile(responseString);
        }
    }

    private void checkHashMapForMultipleMatches(HashMap<String, Value> valueHashMap, DocumentList documentList) {
        for(Value value : documentList.getValue()) {
            if(valueHashMap.containsKey(value.getTitle())) {
                if(valueHashMap.get(value.getTitle()).getWordLength() < value.getWordLength()) valueHashMap.replace(value.getTitle(), value);
            } else {
                valueHashMap.put(value.getTitle(), value);
            }
        }
    }

    private String getValuesAsStringFromListItem(String listItem) {
        HashMap<String, Value> valueHashMap = new HashMap<>();
        DocumentList documentList = requester.requestList(listItem);
        checkHashMapForMultipleMatches(valueHashMap, documentList);
        for(int i = 10; i < documentList.getOdataCount(); i+=10) {
            DocumentList innerDocumentList = requester.requestList(listItem.replace("/v1/News?$", "/v1/News?$skip=" + i + "&$"));
            checkHashMapForMultipleMatches(valueHashMap, innerDocumentList);
        }
        StringBuilder content = new StringBuilder();
        TreeMap<String, Value> sortedMap = new TreeMap<>();
        sortedMap.putAll(valueHashMap);
        for(Value value : sortedMap.values()) {
            content.append(value.toCSV());
        }
        return content.toString();
    }

    public void convertXMLtoTXT() {
        Unmarshaller unmarshaller = null;
        List<String> files = null;
        try (Stream<Path> walker = Files.walk(Paths.get(xmlDirectory.getPath()))) {
            JAXBContext jaxbContext = JAXBContext.newInstance("de.wolfig.response.document");
            unmarshaller = jaxbContext.createUnmarshaller();
            files = walker.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

            for(String file : files) {
                writer.changeWriterSettings(file.replaceAll("xml", "txt"), true);
                try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file))) {
                    Entry entry = (Entry) unmarshaller.unmarshal(inputStreamReader);
                    writer.writeToFile(entry.toTXTString() + "\n");

                    // publication > 2008

                    for (BodyText.P p : entry.getContent().getArticleDoc().getBody().getBodyContent().getBodyText().getP()) {
                        StringBuilder content = new StringBuilder();
                        for(int i = 0; i < p.getContent().size(); i++) {
                            try {
                                JAXBElement<BodyText.P.Person> person = (JAXBElement<BodyText.P.Person>) p.getContent().get(i);
                                content.append(person.getValue().getNameText());
                            } catch (Exception e) {
                                content.append(p.getContent().get(i));
                            }
                        }
                        String cont = content.toString();
                        if(cont.contains(":")) writer.writeToFile("\n ");
                        if(cont.equalsIgnoreCase("Presentation") || cont.equalsIgnoreCase("Questions and Answers")
                                || cont.equalsIgnoreCase("Corporate Participants") || cont.equalsIgnoreCase("Conference Call Participants")
                                || cont.startsWith("THE INFORMATION CONTAINED IN EVENT TRANSCRIPTS IS A TEXTUAL")) writer.writeToFile("\n\n ");
                        writer.writeToFile(content.toString());
                    }
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
