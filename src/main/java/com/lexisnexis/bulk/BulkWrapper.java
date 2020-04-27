package com.lexisnexis.bulk;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.Base64;

import java.net.URLEncoder;
import java.util.UUID;


public class BulkWrapper {
    private static final String REQUEST_HEADER = "X-LN-Request";
    private static String CONTENT_TYPE = "application/x-www-form-urlencoded";

    public static String getAccessToken(String authorizationEndPoint,String clientId, String clientSecret){
        String accessToken = null;
        Client client = Client.create();
        client.addFilter(new LoggingFilter());

        String scopeRequested = "http://oauth.lexisnexis.com/all";
        try {
            WebResource webResource = client.resource(authorizationEndPoint + "/oauth/v2/token");
            String requestbody = "grant_type=client_credentials&scope=" +
                    URLEncoder.encode(scopeRequested, "UTF-8") + "&format=xml";
            String basicAuthId = "Basic " + new String(Base64.encode(clientId + ":" + clientSecret));

            ClientResponse response = webResource
                    .header(REQUEST_HEADER, getRequestHeader())
                    .header("Content-Type", CONTENT_TYPE)
                    .header("Authorization", basicAuthId)
                    .post(ClientResponse.class, requestbody);

            //System.out.println(response.getStatus());

            if(200 == response.getStatus()){
                accessToken = response.getEntity(String.class);
                //System.out.println(accessToken);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public static String getRequestHeader() {
        StringBuffer requestHeader = new StringBuffer();
        requestHeader.append("<rt:requestToken xmlns:rt=\"http://services.lexisnexis.com/xmlschema/request-token/1\">");
        requestHeader.append("<transactionID>");
        requestHeader.append(UUID.randomUUID());
        requestHeader.append("</transactionID>");
        requestHeader.append("<sequence>1</sequence>");
        requestHeader.append("<featurePermID></featurePermID>");
        requestHeader.append("<clientID>-1</clientID>");
        requestHeader.append("<cpmFeatureCode>22</cpmFeatureCode>");
        requestHeader.append("</rt:requestToken>");
        return requestHeader.toString();
    }
}
