package com.huddu;

import org.json.JSONObject;

import java.io.IOException;

public class Events {


    private final String api_key;

    public Events(String api_key) {
        this.api_key = api_key;
    }

    public Object list(String stream, String project, String account, Integer skip, Integer limit) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + stream + "/events?skip=" + skip + "&limit=" + limit;
        return utils._request(api_key, "GET", url);
    }

    public Object get(String id, String stream, String project, String account) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + stream + "/events/" + id;
        return utils._request(api_key, "GET", url);
    }

    public Object search(String stream, String project, String account, JSONObject query, Integer skip, Integer limit) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + stream + "/events/search";

        JSONObject body = new JSONObject();
        body.put("limit", limit);
        body.put("skip", skip);
        body.put("query", query);

        return utils._request(api_key, "POST", url, body);
    }


    public Object create(String stream, String project, String account, String data) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + stream + "/events";

        JSONObject body = new JSONObject();
        body.put("data", data);


        return utils._request(api_key, "POST", url, body);
    }


    public Object create(String stream, String project, String account, String[] batch) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + stream + "/events";

        JSONObject body = new JSONObject();
        body.put("batch", batch);

        return utils._request(api_key, "POST", url, body);
    }

    public Object update(String id, String stream, String project, String account, String meta) throws IOException, InterruptedException {
        String url = "/accounts/" + account + "/projects/" + project + "/streams/" + stream + "/events/" + id;

        JSONObject body = new JSONObject();
        body.put("meta", meta);

        return utils._request(api_key, "PUT", url, body);
    }

}
