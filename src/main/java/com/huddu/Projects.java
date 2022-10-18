package com.huddu;

import java.io.IOException;

public class Projects {


    private String api_key;

    public Projects (String api_key){
        this.api_key = api_key;
    }

    public Object list(String account, Integer skip, Integer limit) throws IOException, InterruptedException {
        String url = "/accounts/"+ account + "/projects?skip="+ skip+ "&limit="+limit;
        return utils._request(api_key, "GET", url);
    }



    public Object get(String id, String account) throws IOException, InterruptedException {
        return utils._request(api_key, "GET", "/accounts/"+ account + "/projects/"+id);
    }

}
