package com.huddu;



import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    Accounts Accounts;

  public  ApiClient(String api_key){
      Accounts  = new Accounts(api_key);
  }

}