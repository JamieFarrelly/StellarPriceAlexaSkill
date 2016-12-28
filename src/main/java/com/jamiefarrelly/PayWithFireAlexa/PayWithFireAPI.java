package com.jamiefarrelly.PayWithFireAlexa;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jamiefarrelly.PayWithFireAlexa.model.Account;
import com.jamiefarrelly.PayWithFireAlexa.model.Accounts;
import com.jamiefarrelly.PayWithFireAlexa.model.ApiAccessToken;
import com.jamiefarrelly.PayWithFireAlexa.model.NewApiAccessTokenRequest;

public class PayWithFireAPI {

    private static RestTemplate restTemplate = new RestTemplate();
    
    private static final String CREATE_ACCESS_TOKEN_URL = "https://api.paywithfire.com/business/v1/apps/accesstokens";
    private static final String GET_ACCOUNT_DETAILS_URL = "https://api.paywithfire.com/business/v1/accounts";
    
    // for now this is hard coded - don't want to store these details for multiple users for example (security wise)
    // IMPORTANT - never commit these details to the likes of Github
    private static final String CLIENT_KEY = "CLIENT_KEY_FROM_FIRE";
    private static final String CLIENT_ID = "CLIENT_ID_FROM_FIRE";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN_FROM_FIRE";
    
    private static final String BEARER_TXT = "Bearer ";
    private static final String AUTH_HEADER_TXT = "Authorization";
    
    /**
     * Gets a list of your Fire accounts
     * 
     * @return List<Account>
     */
    public static List<Account> getAccounts() {
        
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        
        // first, call over to the API to get an access token - see https://paywithfire.com/docs/ for more info
        ApiAccessToken token = getApiAuthToken();
        
        // next, call over to get the details of all of your accounts
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_HEADER_TXT, BEARER_TXT + token.getAccessToken());
        
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Accounts> accountsResponse = restTemplate.exchange(GET_ACCOUNT_DETAILS_URL, HttpMethod.GET, entity, Accounts.class);
        List<Account> accountList = accountsResponse.getBody().getAccounts();
        return accountList;
    }

    /**
     * Calls over to get an auth token so we can call other endpoints like the account details endpoint
     * 
     * @return ApiAccessToken
     */
    private static ApiAccessToken getApiAuthToken() {
        
        NewApiAccessTokenRequest newApiAccessTokenRequest = new NewApiAccessTokenRequest();
        
        Long now = System.currentTimeMillis();
        
        // 3 pieces of info you get from business.paywithfire.com
        newApiAccessTokenRequest.setClientId(CLIENT_ID);
        newApiAccessTokenRequest.setRefreshToken(REFRESH_TOKEN);
        newApiAccessTokenRequest.setClientSecret(DigestUtils.sha256Hex(now + CLIENT_KEY)); // as per docs https://paywithfire.com/docs/ 
        
        newApiAccessTokenRequest.setNonce(now);
        
        ApiAccessToken token = restTemplate.postForObject(CREATE_ACCESS_TOKEN_URL, newApiAccessTokenRequest, ApiAccessToken.class);
        return token;
    }
}