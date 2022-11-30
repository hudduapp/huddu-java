package com.huddu;

import com.huddu._exceptions.APIException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class _sessions {

    private final String baseUrl;
    private final String collection;
    private final String token;
    private final String region;

    _sessions(String collection, String token, String region, String baseUrl) {
        this.collection = collection;
        this.token = token;
        this.region = region;
        this.baseUrl = baseUrl;
    }

    _sessions(String collection, String token, String region) {
        this.collection = collection;
        this.token = token;
        this.region = region;
        this.baseUrl = "https://connect.huddu.io";
    }

    public JSONObject _request(String method, String params, JSONObject data) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        String finalUrl = baseUrl + "/documents";


        switch (method) {
            case "GET":
                finalUrl = baseUrl + "/documents?" + params;
                request = HttpRequest.newBuilder(URI.create(
                                finalUrl
                        )).header("Content-Type", "application/json").header("Authorization", "Token " + token)
                        .header("Collection", collection)
                        .header("Region", region).GET(
                        ).build();
                break;
            case "PUT":
                request = HttpRequest.newBuilder(URI.create(
                                finalUrl
                        )).header("Content-Type", "application/json").header("Authorization", "Token " + token).header("Collection", collection)
                        .header("Region", region).method("PUT", HttpRequest.BodyPublishers.ofString(data.toString())).build();
                break;
            case "DELETE":
                request = HttpRequest.newBuilder(URI.create(
                                finalUrl
                        )).header("Content-Type", "application/json").header("Authorization", "Token " + token).header("Collection", collection)
                        .header("Region", region).method("DELETE", HttpRequest.BodyPublishers.ofString(data.toString())).build();
                break;
            default:
                request = HttpRequest.newBuilder(URI.create(
                                finalUrl
                        )).header("Content-Type", "application/json")
                        .header("Authorization", "Token " + token)
                        .header("Collection", collection)
                        .header("Region", region).method("POST", HttpRequest.BodyPublishers.ofString(data.toString())).build();
                break;
        }

        HttpResponse res = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() > 299 || res.body().toString().contains("error")) {
            System.out.println(res.body().toString());
        }
        return new JSONObject(res.body().toString());
    }


    public JSONObject create_documents(JSONArray items) {
        JSONObject data = new JSONObject();
        data.put("items", items);

        try {
            return _request("POST", null, data);
        } catch (Exception e) {
            return null;
        }

    }


    public JSONObject list_documents(ArrayList ids, int limit, int skip, int start, int end) {
        String params = "ids=" + ids.toString() + "&limit=" + limit + "&skip=" + skip + "&start=" + start + "&end=" + end;

        try {
            return _request("GET", params, null);
        } catch (Exception e) {
            return null;
        }

    }

    public JSONObject list_documents(ArrayList ids, int limit, int skip) {


        StringBuilder idsString = new StringBuilder();

        for (Object id : ids) {
            idsString.append(id).append(",");
        }

        idsString = new StringBuilder(idsString.substring(0, idsString.length() - 1));


        String params = "ids=" + idsString + "&limit=" + limit + "&skip=" + skip;

        try {
            return _request("GET", params, null);
        } catch (Exception e) {
            return null;
        }

    }


    public JSONObject delete_documents(ArrayList ids) {
        JSONObject data = new JSONObject();
        data.put("ids", ids);

        try {
            return _request("DELETE", null, data);
        } catch (Exception e) {
            return null;
        }
    }


    public void setType(String type) throws APIException {
        String typeDocument = null;

        ArrayList ids = new ArrayList();
        ids.add("_type");

        try {
            typeDocument = ((JSONObject) ((JSONArray) list_documents(ids, 1, 0).get("data")).get(0)).get("data").toString();
        } catch (Exception e) {
            JSONArray items = new JSONArray();
            JSONObject newTypeDocument = new JSONObject();

            newTypeDocument.put("id", "_type");
            newTypeDocument.put("data", type);

            items.put(newTypeDocument);

            create_documents(
                    items
            );
        }


        if (typeDocument != null) {
            if (!typeDocument.equals(type)) {
                throw new APIException("Collection is already used for " + typeDocument);
            }
        }

    }
}
