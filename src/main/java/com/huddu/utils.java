package com.huddu;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class utils {


    public static Object _request(String api_key, String method, String url, JSONObject body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String base_url = "https://api.huddu.io";

        String final_url = base_url + url;

        HttpRequest request;


        if (method == "PUT") {
            request = HttpRequest.newBuilder(URI.create(
                    final_url
            )).header("Content-Type", "application/json").header("Authorization", "Token " + api_key).PUT(HttpRequest.BodyPublishers.ofString(body.toString())).build();
        } else if (method == "DELETE") {
            request = HttpRequest.newBuilder(URI.create(
                    final_url
            )).header("Content-Type", "application/json").header("Authorization", "Token " + api_key).DELETE().build();
        } else {
            // could be a different method... for not everything else is a POST
            request = HttpRequest.newBuilder(URI.create(
                    final_url
            )).header("Content-Type", "application/json").header("Authorization", "Token " + api_key).POST(HttpRequest.BodyPublishers.ofString(body.toString())).build();
        }


        HttpResponse res = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (res.statusCode() > 299) {
            System.out.println(res.body().toString());
        }
        return res.body();
    }

    public static Object _request(String api_key, String _method, String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String base_url = "https://api.huddu.io";

        String final_url = base_url + url;

        HttpRequest request = HttpRequest.newBuilder(URI.create(
                final_url
        )).header("Content-Type", "application/json").header("Authorization", "Token " + api_key).GET().build();


        HttpResponse res = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() > 299) {
            System.out.println(res.body().toString());
        }
        return res.body();
    }


    // request without token
    public static Object _request_without_token(String method, String url, JSONObject body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String base_url = "https://api.huddu.io";

        String final_url = base_url + url;

        HttpRequest request;


        if (method == "PUT") {
            request = HttpRequest.newBuilder(URI.create(
                    final_url
            )).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(body.toString())).build();
        } else if (method == "DELETE") {
            request = HttpRequest.newBuilder(URI.create(
                    final_url
            )).header("Content-Type", "application/json").DELETE().build();
        } else {
            // could be a different method... for not everything else is a POST
            request = HttpRequest.newBuilder(URI.create(
                    final_url
            )).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body.toString())).build();
        }


        HttpResponse res = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (res.statusCode() > 299) {
            System.out.println(res.body().toString());
        }
        return res.body();
    }

    public static Object _request_without_token(String _method, String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String base_url = "https://api.huddu.io";

        String final_url = base_url + url;

        HttpRequest request = HttpRequest.newBuilder(URI.create(
                final_url
        )).header("Content-Type", "application/json").GET().build();


        HttpResponse res = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() > 299) {
            System.out.println(res.body().toString());
        }
        return res.body();
    }

}
