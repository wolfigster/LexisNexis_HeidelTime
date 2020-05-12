package de.wolfig;

import de.wolfig.response.list.DocumentList;
import de.wolfig.files.Configuration;
import de.wolfig.files.Reader;
import de.wolfig.files.Writer;
import de.wolfig.lexisnexis.Requester;
import de.wolfig.response.list.Value;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
}
