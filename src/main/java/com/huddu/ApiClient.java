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


    public ApiClient(String project, String stream) {
        baseUrl = "https://ingest.huddu.io";
        this.project = project;
        this.stream = stream;
    }

    public void _request(String eventType, String body) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String url = this.baseUrl + "/" + this.project + "/" + this.stream + "/" + eventType;

        System.out.println(url);
        HttpRequest request = HttpRequest.newBuilder(URI.create(
                url
        )).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body)).build();

        client.send(request, HttpResponse.BodyHandlers.ofString());


    }


    public void report(String eventType, String data) {
        data = data.replace('\'', '"');

        String finalData = data;
        Thread t = new Thread(() -> {
            try {
                this._request(eventType, finalData);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();

    }
}