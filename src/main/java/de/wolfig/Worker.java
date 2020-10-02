package de.wolfig;

import de.wolfig.files.Configuration;
import de.wolfig.files.Reader;
import de.wolfig.files.Writer;
import de.wolfig.lexisnexis.Requester;
import de.wolfig.response.document.BodyText;
import de.wolfig.response.document.Entry;
import de.wolfig.response.list.DocumentList;
import de.wolfig.response.list.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

public class Worker {

    private static final Logger LOGGER = LogManager.getLogger(Worker.class);
    private static final File overviewFile = new File("./overview.csv");
    private static final File listFile = new File("./list.txt");
    private static final File heidelTimeJarFile = new File("./heideltime-standalone/de.unihd.dbs.heideltime.standalone.jar");
    private static final File heidelTimeConfigFile = new File("./heideltime-standalone/config.props");
    private static final File filesDirectory = new File("./files");
    private static final File xmlDirectory = new File(filesDirectory.getPath() + "/xml");
    private static final File txtDirectory = new File(filesDirectory.getPath() + "/txt");
    private static final File htDirectory = new File(filesDirectory.getPath() + "/ht");
    private static final File csvDirectory = new File(filesDirectory.getPath() + "/csv");

    private static final Pattern patternTimex3 = Pattern.compile("<TIMEX3.*?</TIMEX3>");
    private static final Pattern patternTimex3type = Pattern.compile("type=\".*?\"");
    private static final Pattern patternTimex3value = Pattern.compile("value=\".*?\"");
    private static final Pattern patternTimex3mod = Pattern.compile("mod=\".*?\"");
    private static final Pattern patternTimex3message = Pattern.compile("\">.*?<");

    private static final Pattern patternQuarter = Pattern.compile("( )(Q[1-4])([,\\. \\?\\!])");
    private static final Pattern patternYear = Pattern.compile("( )(')([0-9]{2})([,\\. \\?\\!])");

    private static Requester requester = null;
    private static Reader reader = null;
    private static Writer writer = null;

    private static final ArrayList<String> stringsToIgnore = new ArrayList<String>(Arrays.asList("research division", "inc", "inc.", "llc", "llc.", "ltd", "ltd.", "corp", "corp."));

//    public Worker() {
//        requester = new Requester(Configuration.getAccessToken());
//        reader = new Reader();
//        writer = new Writer(listFile, true);
//        createNecessaryDirectories();
//    }

    public static void initialize() {
        requester = new Requester(Configuration.getAccessToken());
        reader = new Reader();
        writer = new Writer(listFile, true);
        createNecessaryDirectories();
    }

    public static void createNecessaryDirectories() {
        if(!filesDirectory.exists()) filesDirectory.mkdir();
        if(!xmlDirectory.exists()) xmlDirectory.mkdir();
        if(!txtDirectory.exists()) txtDirectory.mkdir();
        if(!htDirectory.exists()) htDirectory.mkdir();
        if(!csvDirectory.exists()) csvDirectory.mkdir();
    }

    public static void stop() {
        writer.closeWriter();
    }

    public static void start() {
        writer = new Writer(listFile, true);
    }

    public static String requestListItem(String url) {
        ArrayList<String> documents = new ArrayList<>();
        if(!overviewFile.exists()) {
            writer.changeWriterSettings(overviewFile, true);
            writer.writeToFile("URN;Title;Date;WordLength;Document\n");
        } else {
            for(String line : reader.readFileLineByLine(overviewFile)) documents.add(line.split(";")[1]);
        }
        writer.changeWriterSettings(overviewFile, true);

        int i = 0;
        int documentCount = 0;
        HashMap<String, Value> valueHashMap = new HashMap<>();
        do {
            DocumentList documentList = requester.requestList(url.replace("/v1/News?$", "/v1/News?$skip=" + i + "&$"));

            for(Value value : documentList.getValue()) {
                if(valueHashMap.containsKey(value.getTitle())) {
                    if(valueHashMap.get(value.getTitle()).getWordLength() < value.getWordLength()) valueHashMap.replace(value.getTitle(), value);
                } else {
                    if(!documents.contains(value.getTitle())) valueHashMap.put(value.getTitle(), value);
                }
            }

            if(documentCount == 0) documentCount = documentList.getOdataCount();
            i+=10;
        } while (i < documentCount);

        StringBuilder content = new StringBuilder();
        for(Value value : valueHashMap.values()) content.append(value.toCSV());
        writer.writeToFile(content.toString());
        writer.closeWriter();
        return content.toString();
    }

    public static void requestDocument(String title, String documentURL) {
        writer.changeWriterSettings(xmlDirectory.getPath() + File.separator + title + ".xml", false);
        writer.writeToFile(requester.request("https://services-api.lexisnexis.com/v1/" + documentURL));
        writer.closeWriter();
    }

    public static void convertXMLtoTXT(String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("de.wolfig.response.document");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            writer.changeWriterSettings(filePath.replace("xml", "txt"), false);
            try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath))) {
                Entry entry = (Entry) unmarshaller.unmarshal(inputStreamReader);
                writer.writeToFile(entry.toTXTString());
                writer.changeWriterSettings(filePath.replace("xml", "txt"), true);

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
                    String inFrontOfColon = cont.split(":")[0];
                    if(inFrontOfColon.equals(inFrontOfColon.toUpperCase())) writer.writeToFile("\n");
                    if(cont.equalsIgnoreCase("Presentation") || cont.equalsIgnoreCase("Questions and Answers")
                            || cont.equalsIgnoreCase("Corporate Participants") || cont.equalsIgnoreCase("Conference Call Participants")
                            || cont.startsWith("THE INFORMATION CONTAINED IN EVENT TRANSCRIPTS IS A TEXTUAL")) writer.writeToFile("\n\n");
                    writer.writeToFile(content.toString());
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        writer.closeWriter();
    }

    public static void executeHeidelTime(String txtFile) {
        String publishedDate = null;
        StringBuilder stringBuilder = new StringBuilder();
        boolean id = false;

        writer.changeWriterSettings(txtFile.replaceAll("txt", "xml").replaceFirst("xml", "ht"), false);

        try {
            publishedDate = Files.readAllLines(Paths.get(txtFile), StandardCharsets.UTF_8).get(2).substring(7,17);
        } catch (IOException e) {
            try {
                publishedDate = Files.readAllLines(Paths.get(txtFile), StandardCharsets.ISO_8859_1).get(2).substring(7,17);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        String[] command = new String[] {"java", "-jar", heidelTimeJarFile.getAbsolutePath(), txtFile, "-dct", publishedDate, "-t", "NEWS", "-c", heidelTimeConfigFile.getAbsolutePath()};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            Process process = processBuilder.start();
            int c = 0;
            InputStream inputStream = process.getInputStream();
            while ((c = inputStream.read()) != -1) {
                stringBuilder.append((char)c);
                if(c == 10) {
                    if(id) {
                        String inFrontOfColon = stringBuilder.toString().split(":")[0];
                        if(inFrontOfColon.equals(inFrontOfColon.toUpperCase()) && stringBuilder.toString().contains(":")) inFrontOfColon = stringBuilder.toString().replaceFirst(inFrontOfColon, "<PERSON>" + inFrontOfColon + "</PERSON>");
                        writer.writeToFile(inFrontOfColon);
                    } else {
                        if(stringBuilder.toString().startsWith("$")) writer.writeToFile(stringBuilder.toString().replaceAll("<.*?>", ""));
                        else writer.writeToFile(stringBuilder.toString());
                    }
                    if(stringBuilder.toString().startsWith("$ID")) id = true;
                    stringBuilder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.closeWriter();
    }

    public static void createCSV(String heidelTimeFile) {
        int year = 0;
        String quarter = "Q1";
        boolean passedPresentation = false;
        String presOrQA = "P";
        ArrayList<ImpPerson> persons = new ArrayList<>();
        String publication = "";
        int i = 1;
        int lineNumber = (DateRule.getLinesBasedOn().equals("ht")) ? 1 : -2;
        writer.changeWriterSettings(heidelTimeFile.replace("xml", "csv").replaceFirst("ht", "csv"), false);
        StringBuilder stringBuilder = new StringBuilder();
        for(int number = 0; number < DateRule.getRuleAmount(); number++) stringBuilder.append("Actual Date ").append(number).append(";").append("Distance ").append(number).append(";");
        writer.writeToFile("Number;Person;Name;Position;CEO;Company;Year;Quarter;Q&A;Type;TIMEX3;Publication;Date;" + stringBuilder.toString() + ";Line " + DateRule.getLinesBasedOn() + "\n");
        writer.changeWriterAppend(true);
        for(String line : reader.readFileLineByLine(new File(heidelTimeFile))) {

            if(line.startsWith("$Title: ")) {
                if(year == 0 && quarter.equals("Q1")) {
                    Matcher matchYear = Pattern.compile("([0-9]{4})").matcher(line);
                    while(matchYear.find()) year = Integer.parseInt(matchYear.group());
                    Matcher matchQuarter = Pattern.compile("(Q[1-4])").matcher(line);
                    while(matchQuarter.find()) quarter = matchQuarter.group();
                }
            }

            for(int l = 0; l < 2; l++) {
                StringBuffer stringBuffer = new StringBuffer(line.length());
                if (l == 0) {
                    Matcher match = patternQuarter.matcher(line);
                    while (match.find()) match.appendReplacement(stringBuffer, Matcher.quoteReplacement(match.group(1) + "<TIMEX3 tid=\"" + getRandom4DigitNumber() + "\" type=\"DATE\" + value=\"" + publication.split("-")[0] + "-" + match.group(2) + "\">" + match.group(2) + "</TIMEX3>" + match.group(3)));
                    match.appendTail(stringBuffer);
                } else {
                    Matcher match = patternYear.matcher(line);
                    while (match.find()) match.appendReplacement(stringBuffer, Matcher.quoteReplacement(match.group(1) + "<TIMEX3 tid=\"" + getRandom4DigitNumber() + "\" type=\"DATE\" + value=\"20" + match.group(3) + "\">" + match.group(2) + match.group(3) + "</TIMEX3>" + match.group(4)));
                    match.appendTail(stringBuffer);
                }
                line = stringBuffer.toString();
            }
            if(year >= 2008) {
                if(!passedPresentation && line.equalsIgnoreCase("questions and answers")) {
                    passedPresentation = true;
                    presOrQA = "Q";
                }
            }

            if(line.startsWith("$Date")) publication = line.substring(7,17);
            if(line.startsWith("<PERSON>")) {
                Matcher matcher = patternTimex3.matcher(line);
                String person = line.substring(line.indexOf("<PERSON>")+8,line.indexOf("</PERSON>"));
                String[] personArr = person.split(", ");
                String name = personArr[0];
                String position = "";
                String company = "";
                String ceo = "";

                ImpPerson impPerson = new ImpPerson("", "", "", "OPERATOR");
                if(persons.stream().noneMatch(p -> p.getName().equalsIgnoreCase(name))) {
                    if(personArr.length >= 3) {
                        int length = personArr.length-1;
                        String lastIndex = personArr[length];
                        while(stringsToIgnore.contains(lastIndex.toLowerCase())) {
                            length--;
                            lastIndex = personArr[length];
                        }
                        company = lastIndex;
                        for(int l = 1; l < length; l++) position = position + ", " + personArr[l];

                        Matcher matchCEO = Pattern.compile("(C[A-Z]O)").matcher(position);
                        while(matchCEO.find()) {
                            if(ceo.equals("")) ceo = matchCEO.group(1);
                            System.out.println("### " + ceo);
                        }
                        matchCEO = Pattern.compile("((C)[A-Z]* ([A-Z])[A-Z]* (O)[A-Z]*)").matcher(position);
                        while(matchCEO.find()) {
                            if(ceo.equals("")) ceo = matchCEO.group(2) + matchCEO.group(3) + matchCEO.group(4);
                            System.out.println(heidelTimeFile);
                            System.out.println("*** " + ceo);
                        }
                        if(ceo.equals("") && (position.contains("IR") || position.contains("INVESTOR RELATION"))) ceo = "IR";
                        if(ceo.equals("") && (position.contains("ANALYST") || position.contains("MD") || position.contains("PORTFOLIO MANAGER") || position.contains("CFA") || position.contains("CHIEF FINANCIAL ANALYST"))) {
                            ceo = "ANALYST";
                            System.out.println("--- " + ceo);
                            if(!passedPresentation) {
                                passedPresentation = true;
                                presOrQA = "Q";
                            }
                        }
                        if(ceo.equals("") && position.contains("SVP")) ceo = "SVP";
                        if(ceo.equals("") && position.contains("PRESIDENT")) ceo = "PRESIDENT";

                        impPerson = new ImpPerson(name, position.replaceFirst(", ", ""), company, ceo);
                        persons.add(impPerson);
                    }
                } else {
                    impPerson = persons.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
                }


                while(matcher.find()) {
                    String type = "";
                    Matcher matcher1 = patternTimex3type.matcher(matcher.group());
                    while(matcher1.find()) type = matcher1.group();
                    type = type.substring(6, type.length()-1);
                    String date = "";
                    Matcher matcher2 = patternTimex3value.matcher(matcher.group());
                    while(matcher2.find()) date = matcher2.group();
                    date = date.substring(7, date.length()-1);
                    String timex3msg = "";
                    Matcher matcher3 = patternTimex3message.matcher(matcher.group());
                    while(matcher3.find()) timex3msg = matcher3.group();
                    timex3msg = timex3msg.substring(2, timex3msg.length()-1);
                    String timex3mod = "";
                    Matcher matcher4 = patternTimex3mod.matcher(matcher.group());
                    while(matcher4.find()) timex3mod = matcher4.group();
                    if(!timex3mod.equals("")) timex3mod = timex3mod.substring(5, timex3mod.length()-1);
                    // add modifier mod

                    // distance calculation required
                    stringBuilder = new StringBuilder();
                    for(int number = 0; number < DateRule.getRuleAmount(); number++) {
                        String actualDate = DateRule.calculateDate(number, type, date, timex3mod, publication);
                        LocalDate actualLocalDate = LocalDate.parse(actualDate);
                        LocalDate publicationLocalDate = LocalDate.parse(publication);
                        String distance = String.valueOf(DAYS.between(publicationLocalDate, actualLocalDate));
                        stringBuilder.append(actualDate).append(";").append(distance).append(";");
                    }
                    timex3mod = timex3mod.equals("START") || timex3mod.equals("MID") || timex3mod.equals("END") ? " (" + timex3mod + ")" : "";

                    writer.writeToFile(i + ";" + person + ";" + impPerson.getName() + ";" + impPerson.getPosition() + ";" + impPerson.getCeo() + ";" + impPerson.getCompany() + ";" + year + ";" + quarter + ";" + presOrQA + ";" + type + ";" + timex3msg + ";" + publication + ";" + date + timex3mod + ";" + stringBuilder.toString() + ";" + lineNumber + "\n");
                    i++;
                }
            }
            lineNumber++;
        }
        writer.closeWriter();
    }

    private static int getRandom4DigitNumber() {
        return new Random().nextInt(8999) + 1000;
    }





    public void initializeList(int startingLine) {
        long lineCount = 0;
        try {
            lineCount = Files.lines(overviewFile.toPath()).count(); // maybe +1
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(startingLine == 0) {
            writer.changeWriterSettings(overviewFile, false);
            writer.writeToFile("URN;Title;Date;WordLength;Document\n");
            lineCount = 0;
        }
        writer.changeWriterSettings(overviewFile, true);
        for(String listItem : reader.readFileLineByLineFromLine(listFile, startingLine)) {
            writer.writeToFile(getValuesAsStringFromListItem(listItem));
        }
        requestAllDocuments((int) lineCount);
    }

    private void requestAllDocuments(int startingLine) {
        String[] response = null;
        String responseString = null;
        for(String responseItem : reader.readFileLineByLineFromLine(overviewFile, startingLine)) {
            response = responseItem.split(";");
            if(response[0].equals("URN")) continue;
            responseString = requester.request("https://services-api.lexisnexis.com/v1/" + response[4]);
            try {
                TimeUnit.MILLISECONDS.sleep(500); // -> max 120 requests per minute (limit: 125)
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
        HashMap<String, Integer> speakerCount = new HashMap<>();
        try (Stream<Path> walker = Files.walk(Paths.get(xmlDirectory.getPath()))) {
            JAXBContext jaxbContext = JAXBContext.newInstance("de.wolfig.response.document");
            unmarshaller = jaxbContext.createUnmarshaller();
            files = walker.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

            for(String file : files) {
                writer.changeWriterSettings(file.replaceAll("xml", "txt"), false);
                try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file))) {
                    Entry entry = (Entry) unmarshaller.unmarshal(inputStreamReader);
                    writer.writeToFile(entry.toTXTString());
                    writer.changeWriterSettings(file.replaceAll("xml", "txt"), true);

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
                        String inFrontOfColon = cont.split(":")[0];
                        if(inFrontOfColon.equals(inFrontOfColon.toUpperCase())) writer.writeToFile("\n");
                        if(cont.equalsIgnoreCase("Presentation") || cont.equalsIgnoreCase("Questions and Answers")
                                || cont.equalsIgnoreCase("Corporate Participants") || cont.equalsIgnoreCase("Conference Call Participants")
                                || cont.startsWith("THE INFORMATION CONTAINED IN EVENT TRANSCRIPTS IS A TEXTUAL")) writer.writeToFile("\n\n");
                        writer.writeToFile(content.toString());
                    }
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void updateHeidelTimeConfig() {
        File heidelTimeConfig = new File("./heideltime-standalone/config.props");
        File treeTagger = new File(".");
        String treeTaggerPath = treeTagger.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\");
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> lines = reader.readFileLineByLine(heidelTimeConfig);
        writer.changeWriterSettings(heidelTimeConfig, false);
        ListIterator<String> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.startsWith("treeTaggerHome")) {
                stringBuilder.append("treeTaggerHome = " + treeTaggerPath.substring(0, treeTaggerPath.length()-1) + "TreeTagger\n");
            } else {
                stringBuilder.append(next + "\n");
            }
        }
        writer.writeToFile(stringBuilder.toString());
    }

    public void executeHeidelTime() {
        List<String> files = null;
        String publishedDate = null;
        StringBuilder test = new StringBuilder();
        File heidelTimeJar = new File("./heideltime-standalone/de.unihd.dbs.heideltime.standalone.jar");
        File heidelTimeConfig = new File("./heideltime-standalone/config.props");
        try (Stream<Path> walker = Files.walk(Paths.get(new File("./files/txt").getPath()))) {
            files = walker.filter(Files::isRegularFile).map(x -> x.toAbsolutePath().toString()).collect(Collectors.toList());
            for(String file : files) {
                boolean id = false;
                try {
                    publishedDate = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8).get(2).substring(7,17);
                } catch (IOException e) {
                    publishedDate = Files.readAllLines(Paths.get(file), StandardCharsets.ISO_8859_1).get(2).substring(7,17);
                }
                writer.changeWriterSettings(file.replaceAll("txt", "xml").replaceFirst("xml", "ht"), false);
                String[] command = new String[] {"java", "-jar", heidelTimeJar.getAbsolutePath(), file, "-dct", publishedDate, "-t", "NEWS", "-c", heidelTimeConfig.getAbsolutePath()};
                ProcessBuilder builder = new ProcessBuilder(command);
                try {
                    Process process = builder.start();
                    int c = 0;
                    InputStream inputStream = process.getInputStream();
                    while ((c = inputStream.read()) != -1) {
                        test.append((char)c);
                        if(c == 10) {
                            if(id) {
                                String inFrontOfColon = test.toString().split(":")[0];
                                if(inFrontOfColon.equals(inFrontOfColon.toUpperCase()) && test.toString().contains(":")) inFrontOfColon = test.toString().replaceFirst(inFrontOfColon, "<PERSON>" + inFrontOfColon + "</PERSON>");
                                writer.writeToFile(inFrontOfColon);
                            } else {
                                if(test.toString().startsWith("$")) writer.writeToFile(test.toString().replaceAll("<.*?>", ""));
                                else writer.writeToFile(test.toString());
                            }
                            if(test.toString().startsWith("$ID")) id = true;
                            test = new StringBuilder();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOutputCSV() {
        List<String> files = null;
        Pattern timex3 = Pattern.compile("<TIMEX3.*?</TIMEX3>");
        Pattern timex3type = Pattern.compile("type=\".*?\"");
        Pattern timex3value = Pattern.compile("value=\".*?\"");
        Pattern timex3message = Pattern.compile("\">.*?<");
        try (Stream<Path> walker = Files.walk(Paths.get(new File("./files/ht").getPath()))) {
            files = walker.filter(Files::isRegularFile).map(x -> x.toAbsolutePath().toString()).collect(Collectors.toList());
            for(String file : files) {
                String publication = null;
                int i = 0;
                int lineNumber = 1;
                writer.changeWriterSettings(file.replace("xml", "csv").replaceFirst("ht", "csv"), false);
                writer.writeToFile("Number;Person;Jobtitle;Type;Date;TIMEX3;Publication;Line;Distance\n");
                writer.changeWriterAppend(true);
                for(String line : reader.readFileLineByLine(new File(file))) {
                    if(line.startsWith("$Date")) publication = line.substring(7,17);
                    if(line.startsWith("<PERSON>")) {
                        Matcher matcher = timex3.matcher(line);
                        String person = line.substring(line.indexOf("<PERSON>")+8,line.indexOf("</PERSON>"));
                        String jobTitle = "";
                        while(matcher.find()) {
                            String type = null;
                            Matcher typeMatcher = timex3type.matcher(matcher.group());
                            while(typeMatcher.find()) type = typeMatcher.group();
                            type = type.substring(6, type.length()-1);
                            String date = null;
                            Matcher dateMatcher = timex3value.matcher(matcher.group());
                            while(dateMatcher.find()) date = dateMatcher.group();
                            date = date.substring(7, date.length()-1);
                            String timex3msg = null;
                            Matcher timex3msgMatcher = timex3message.matcher(matcher.group());
                            while(timex3msgMatcher.find()) timex3msg = timex3msgMatcher.group();
                            timex3msg = timex3msg.substring(2, timex3msg.length()-1);
                            String distance = "0";
                            writer.writeToFile(i + ";" + person + ";" + jobTitle + ";" + type + ";" + date + ";" + timex3msg + ";" + publication + ";" + lineNumber + ";" + distance + "\n");
                            i++;
                        }
                    }
                    lineNumber++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
