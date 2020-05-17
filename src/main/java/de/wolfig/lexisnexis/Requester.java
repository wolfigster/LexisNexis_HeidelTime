package de.wolfig.lexisnexis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wolfig.response.list.DocumentList;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Requester {

    private static final Logger LOGGER = LogManager.getLogger(Requester.class);
    private final String accessToken;

    public Requester(String accessToken) {
        this.accessToken = accessToken;
    }

    public DocumentList requestList(String url) {
        String abc = request(url);
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentList documentList = null;
        try {
            documentList = objectMapper.readValue(abc, DocumentList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return documentList;
    }

    public String request(String url) {
        HttpResponse<String> response = Unirest.get(url)
                .header("Accept", "application/json;odata.metadata=minimal")
                .header("Authorization", "Bearer " + accessToken)
                .header("Host", "services-api.lexisnexis.com")
                .asString();

        if (String.valueOf(response.getStatus()).startsWith("2")) {
            LOGGER.info("Status " + response.getStatus() + ": " + url);
        } else {
            LOGGER.warn("Status " + response.getStatus() + ": " + url);
        }

        return response.getBody();
    }
}
