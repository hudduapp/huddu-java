package com.huddu;

import org.json.JSONObject;

import java.io.IOException;

public class Streams {


    private final String api_key;

    public Streams(String api_key) {
        this.api_key = api_key;
    }

    public Object list(String project, String account, Integer skip, Integer limit) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams?skip=" + skip + "&limit=" + limit;
        return utils._request(api_key, "GET", url);
    }


    public Object get(String id, String project, String account) throws IOException, InterruptedException {
        return utils._request(api_key, "GET", "/accounts/" + account + "/projects/" + project + "/streams/" + id);
    }


    public Object create(String id, String project, String account, String name) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams";

        JSONObject body = new JSONObject();
        body.put("name", name);

        return utils._request(api_key, "POST", url, body);
    }


    public Object list_versions(String id, String project, String account, Integer skip, Integer limit) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + id + "/versions?skip=" + skip + "&limit=" + limit;
        return utils._request(api_key, "GET", url);
    }


    public Object create_version(String id, String project, String account, String version, String name) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + id + "/versions";

        JSONObject body = new JSONObject();
        body.put("version", version);
        body.put("name", name);

        return utils._request(api_key, "POST", url, body);
    }


}
