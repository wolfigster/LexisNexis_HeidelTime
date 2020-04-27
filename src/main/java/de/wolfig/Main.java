package de.wolfig;

import com.lexisnexis.bulk.BulkWrapper;
import com.lexisnexis.bulk.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String clientId = null;
        String secret = null;
        String access_token = null;
        File config = new File("./config.xml");

        if(System.currentTimeMillis() - config.lastModified() > 86400000) {
            try {
                Properties properties = new Properties();
                FileInputStream fileInputStream = new FileInputStream(config);
                properties.loadFromXML(fileInputStream);
                fileInputStream.close();
                clientId = properties.getProperty("clientid");
                secret = properties.getProperty("secret");

                access_token = BulkWrapper.getAccessToken(clientId, secret);
                access_token = Util.getXMLValue(access_token, "<access_token>", "</access_token>");

                FileOutputStream fileOutputStream = new FileOutputStream(config);
                properties.setProperty("access_token", access_token);
                properties.storeToXML(fileOutputStream, "access_token renewed at " + System.nanoTime());
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
