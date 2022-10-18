package com.huddu;

import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

public class Example {

    public static void main(String[] args) throws IOException, InterruptedException {
        String project_id = "6979386863354290176";  // todo: replace as needed
        String stream_id = "6987388048929628160";  // todo: replace as needed
        String account_id = "6966809249058037760";  // todo: replace as needed
        String event_id = "6987851870576484352";  // todo: replace as needed

        ApiClient cl = new ApiClient("c5a2738274c2f47cf801108d07afe2a9");


        // ACCOUNTS
        //list projects
        System.out.println(
                cl.Accounts.list_installations(0, 25)
        );

        // get account
        System.out.println(
                cl.Accounts.get(account_id)
        );

        //PROJECTS
        //list projects
        System.out.println(
                cl.Projects.list(account_id, 0, 25)
        );

        //get project
        System.out.println(
                cl.Projects.get(project_id, account_id)
        );

        //STREAMS
        //list streams
        System.out.println(
                cl.Streams.list(
                        project_id, account_id, 0, 25
                )
        );


        // get stream
        System.out.println(
                cl.Streams.get(
                        stream_id,
                        project_id,
                        account_id
                )
        );

        //create stream
        System.out.println(
                cl.Streams.create(stream_id, project_id, account_id, "new_stream")
        );


        // list versions
        System.out.println(
                cl.Streams.list_versions(stream_id, project_id, account_id, 0, 25)
        );

        //create stream version
        System.out.println(
                cl.Streams.create_version(stream_id, project_id, account_id, UUID.randomUUID().toString(), "new_version")
        );

        //EVENTS
        // list events
        System.out.println(
                cl.Events.list(
                        stream_id, project_id, account_id, 0, 25
                )
        );

        //get event
        System.out.println(
                cl.Events.get(
                        event_id, stream_id, project_id, account_id
                )
        );


        //search events


        JSONObject query = new JSONObject();
        query.put(
                "data.example", new JSONObject().put("$regex", "string")
        );

        System.out.println(
                cl.Events.search(stream_id, project_id, account_id, query, 0, 25)
        );
        //create event
        System.out.println(
                cl.Events.create(stream_id, project_id, account_id, "my_data")
        );

        //batch create events
        String[] data = new String[3];
        for (int i = 0; i <= 2; i++) {
            data[i] = "hello";
        }
        System.out.println(data);

        System.out.println(
                cl.Events.create(stream_id, project_id, account_id, data)
        );

        Object res = cl.Accounts.list_installations(0, 25);
        System.out.println(res);
    }
}