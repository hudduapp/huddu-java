package com.huddu;


public class ApiClient {

    public Accounts Accounts;
    public Projects Projects;
    public Streams Streams;
    public Events Events;

    public ApiClient(String api_key) {
        Accounts = new Accounts(api_key);

        Projects = new Projects(api_key);

        Streams = new Streams(api_key);

        Events = new Events(api_key);
    }

}