package de.wolfig;

import com.lexisnexis.bulk.BulkWrapper;
import com.lexisnexis.bulk.Util;

public class Main {
    public static void main(String[] args) {
        String authorizationEndPoint = "https://auth-api.lexisnexis.com";
        String clientId = "CLIENT_ID";
        String secret = "SECRET";

        String accessToken = BulkWrapper.getAccessToken(authorizationEndPoint,clientId,secret);

        accessToken = Util.getXMLValue(accessToken, "<access_token>", "</access_token>");
        System.out.println(accessToken);
    }
}
