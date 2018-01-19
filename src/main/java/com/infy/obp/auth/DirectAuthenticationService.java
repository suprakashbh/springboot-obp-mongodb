package com.infy.obp.auth;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DirectAuthenticationService {

    @Value("${obp.api.directloginUrl}")
    private String directLoginUrl;

    @Value("${obp.consumerKey}")
    private String consumerKey;

    public String login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String dlData = String.format("DirectLogin username=%s,password=%s,consumer_key=%s", username, password, consumerKey);
        HttpHeaders authHeaders = new HttpHeaders();
        String token = "";
        authHeaders.add(HttpHeaders.AUTHORIZATION, dlData);
        HttpEntity<Void> req = new HttpEntity<>(null, authHeaders);
        try{
        ResponseEntity<Token> response = restTemplate.exchange(directLoginUrl, HttpMethod.POST, req, Token.class);
        token = response.getBody().getToken();
        }catch (Exception e) {
			System.out.println(e);
		}
        return token;
    }
    
    public String login(String username, String password,String custKey,String loginURL) {
        RestTemplate restTemplate = new RestTemplate();
        String dlData = String.format("DirectLogin username=%s,password=%s,consumer_key=%s", username, password, custKey);
        HttpHeaders authHeaders = new HttpHeaders();
        String token = "";
        authHeaders.add(HttpHeaders.AUTHORIZATION, dlData);
        HttpEntity<Void> req = new HttpEntity<>(null, authHeaders);
        try{
        ResponseEntity<Token> response = restTemplate.exchange(loginURL, HttpMethod.POST, req, Token.class);
        token = response.getBody().getToken();
        }catch (Exception e) {
			System.out.println(e);
		}
        return token;
    }

    @Data
    private static class Token {
        private String token;
    }

}
