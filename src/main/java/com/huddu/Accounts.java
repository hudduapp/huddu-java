package com.huddu;

import java.io.IOException;

public class Accounts {


    private String api_key;

    public Accounts (String api_key){
        this.api_key = api_key;
    }

    public Object list_installations(Integer skip, Integer limit) throws IOException, InterruptedException {
        String url = "/installations?skip="+ skip+ "&limit="+limit;
        return utils._request(api_key, "GET", url);
    }



    public Object get(String id) throws IOException, InterruptedException {
        return utils._request(api_key, "GET", "/accounts/"+id);
    }



}
