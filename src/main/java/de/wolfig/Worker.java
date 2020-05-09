package de.wolfig;

import de.wolfig.response.RObject;
import de.wolfig.files.Configuration;
import de.wolfig.files.Reader;
import de.wolfig.files.Writer;
import de.wolfig.lexisnexis.Requester;

import java.util.concurrent.TimeUnit;

public class Worker {

    private final String responseFilePath = "./response.csv";
    private final String listFilePath = "./list.txt";
    private Requester requester = null;
    private Reader reader = null;
    private Writer writer = null;

    public Worker() {
        requester = new Requester(Configuration.getAccessToken());
        reader = new Reader();
        writer = new Writer(responseFilePath, true);
    }

    public void initializeList() {
        for(String listItem : reader.readFileLineByLine(listFilePath)) {
            RObject rObject = requester.requestList(listItem);
            writer.changeWriterFile(responseFilePath);
            writer.writeRObjectToCSV(rObject);
            for(int i = 10; i < rObject.getOdataCount(); i+=10) {
                RObject innerRObject = requester.requestList(listItem.replace("/v1/News?$", "/v1/News?$skip=" + i + "&$"));
                writer.writeRObjectToCSV(innerRObject);
            }
        }
        requestAllDocuments();
    }

    private void requestAllDocuments() {
        String[] response = null;
        String responseString = null;
        for(String responseItem : reader.readFileLineByLine(responseFilePath)) {
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
