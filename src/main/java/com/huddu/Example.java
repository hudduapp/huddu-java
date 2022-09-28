package com.huddu;

import org.json.JSONObject;

public class Example {
    public static void main(String[] args) {

        // An example:
        ApiClient _c = new ApiClient("<project_id>", "<stream_id>"); // you can optionally also pass a token

        JSONObject data = new JSONObject();
        data.put("<key>","<value>");

        _c.report(data);

    }
}