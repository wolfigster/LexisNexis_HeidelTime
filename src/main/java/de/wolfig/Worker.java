package de.wolfig;

import de.wolfig.response.list.DocumentList;
import de.wolfig.files.Configuration;
import de.wolfig.files.Reader;
import de.wolfig.files.Writer;
import de.wolfig.lexisnexis.Requester;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Worker {

    private final File overviewFile = new File("./overview.csv");
    private final File listFile = new File("./list.txt");
    private final File xmlDirectory = new File("./files/xml");
    private final File txtDirectory = new File("./files/txt");
    private final File csvDirectory = new File("./files/csv");
    private Requester requester = null;
    private Reader reader = null;
    private Writer writer = null;

    public Worker() {
        requester = new Requester(Configuration.getAccessToken());
        reader = new Reader();
        writer = new Writer(overviewFile, true);
        createDirectoriesIfNecessary();
    }

    public void createDirectoriesIfNecessary() {
        if(!xmlDirectory.exists()) xmlDirectory.mkdir();
        if(!txtDirectory.exists()) txtDirectory.mkdir();
        if(!csvDirectory.exists()) csvDirectory.mkdir();
    }

    public void initializeList() {
        for(String listItem : reader.readFileLineByLine(listFile)) {
            DocumentList documentList = requester.requestList(listItem);
            writer.changeWriterFile(overviewFile);
            writer.writeRObjectToCSV(documentList);
            for(int i = 10; i < documentList.getOdataCount(); i+=10) {
                DocumentList innerDocumentList = requester.requestList(listItem.replace("/v1/News?$", "/v1/News?$skip=" + i + "&$"));
                writer.writeRObjectToCSV(innerDocumentList);
            }
        }
        requestAllDocuments();
    }

    private void requestAllDocuments() {
        String[] response = null;
        String responseString = null;
        for(String responseItem : reader.readFileLineByLine(overviewFile)) {
            response = responseItem.split(";");
            responseString = requester.request("https://services-api.lexisnexis.com/v1/" + response[4]);
            try {
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.changeWriterSettings("./output/" + response[1].toString() + ".xml", false);
            writer.writeToFile(responseString);
        }
    }

    public void stop() {
        writer.closeWriter();
    }
}
