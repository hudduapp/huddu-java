package com.huddu;

public class Example {
    public static void main(String[] args) {
        // An example:
        ApiClient _c = new ApiClient("<project_id>", "<stream_id>"); // you can optionally also pass a token
        _c.report( "<valid_json_string>");

    }
}