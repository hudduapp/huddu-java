package com.huddu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    String baseUrl;
    String project;
    String stream;
    String token = "";


    public ApiClient(String project, String stream, String token) {
        baseUrl = "https://ingest.huddu.io";
        this.project = project;
        this.stream = stream;
        this.token = token;
    }

    public ApiClient(String project, String stream) {
        baseUrl = "https://ingest.huddu.io";
        this.project = project;
        this.stream = stream;

    }

    public void _request(String body) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String url = this.baseUrl + "/" + this.project + "/" + this.stream ;

        HttpRequest request = HttpRequest.newBuilder(URI.create(
                url
        )).header("Content-Type", "application/json").header("Authorization", "Token "+ this.token).POST(HttpRequest.BodyPublishers.ofString(body)).build();


        HttpResponse res  =  client.send(request, HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() > 299){
            System.out.println(res.body());
        }
    }


    public void report(String data) {

        String body = "";

        body +="{'data':"+data+"}";

        body = body.replace('\'', '"');

        String finalBody = body;
        Thread t = new Thread(() -> {
            try {
                this._request( finalBody);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();

    }
}