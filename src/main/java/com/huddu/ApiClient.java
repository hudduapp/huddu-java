package com.huddu;


public class ApiClient {

    Accounts Accounts;
    Projects Projects;
    Streams Streams;
    Events Events;

    public ApiClient(String api_key) {
        Accounts = new Accounts(api_key);

        Projects = new Projects(api_key);

        Streams = new Streams(api_key);

        Events = new Events(api_key);
    }

}