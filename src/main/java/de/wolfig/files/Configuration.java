package de.wolfig.files;

import com.lexisnexis.bulk.BulkWrapper;
import com.lexisnexis.bulk.Util;
import de.wolfig.DateRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static final Logger LOGGER = LogManager.getLogger(Configuration.class);
    private static final File CONFIG = new File("./config.xml");
    private static final File DATERULE = new File("./daterule.xml");
    private static Properties properties = null;
    private static String clientId = null;
    private static String clientSecret = null;
    private static String accessToken = null;
    private static long accessExpired = 0;

    private static void updateAccessToken() {
        try {
            accessToken = BulkWrapper.getAccessToken(clientId, clientSecret);
            accessToken = Util.getXMLValue(accessToken, "<access_token>", "</access_token>");

            FileOutputStream fileOutputStream = new FileOutputStream(CONFIG);
            properties.setProperty("access.token", accessToken);
            properties.setProperty("access.expired", String.valueOf(System.currentTimeMillis()));
            properties.storeToXML(fileOutputStream, null);
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isAccessTokenExpired() {
        return System.currentTimeMillis() - accessExpired > 86400000;
    }

    public static void loadConfiguration() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(CONFIG);
            properties.loadFromXML(fileInputStream);
            fileInputStream.close();
            clientId = properties.getProperty("client.id");
            clientSecret = properties.getProperty("client.secret");
            accessExpired = Long.parseLong(properties.getProperty("access.expired"));

            if(isAccessTokenExpired()) updateAccessToken();

            accessToken = properties.getProperty("access.token");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(DATERULE);
            properties.loadFromXML(fileInputStream);
            fileInputStream.close();
            DateRule.set(properties.getProperty("linesBasedOn"),
                    properties.getProperty("date.past"),
                    properties.getProperty("date.future"),
                    properties.getProperty("date.week"),
                    properties.getProperty("date.month"),
                    properties.getProperty("date.year"),
                    properties.getProperty("date.quarter"),
                    properties.getProperty("date.halfYear"),
                    properties.getProperty("date.weekend"),
                    properties.getProperty("date.season"),
                    properties.getProperty("duration.days"),
                    properties.getProperty("duration.weeks"),
                    properties.getProperty("duration.months"),
                    properties.getProperty("duration.quarters"),
                    properties.getProperty("duration.years"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAccessToken() {
        return accessToken;
    }
}
