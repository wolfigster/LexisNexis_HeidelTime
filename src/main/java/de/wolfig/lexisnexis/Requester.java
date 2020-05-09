package de.wolfig.lexisnexis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wolfig.response.list.DocumentList;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Requester {

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

        return response.getBody();
    }
}
