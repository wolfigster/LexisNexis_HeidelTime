package de.wolfig.lexisnexis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.wolfig.response.RObject;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Requester {

    private final String accessToken;

    public Requester(String accessToken) {
        this.accessToken = accessToken;
    }

    public RObject requestList(String url) {
        String abc = request(url);
        ObjectMapper objectMapper = new ObjectMapper();
        RObject RObject = null;
        try {
            RObject = objectMapper.readValue(abc, RObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return RObject;
    }

    private String request(String url) {
        HttpResponse<String> response = Unirest.get(url)
                .header("Accept", "application/json;odata.metadata=minimal")
                .header("Authorization", "Bearer " + accessToken)
                .header("Host", "services-api.lexisnexis.com")
                .asString();

        return response.getBody();
    }
}
