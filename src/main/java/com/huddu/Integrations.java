package com.huddu;

import org.json.JSONObject;

import java.io.IOException;

public class Integrations {


    private final String integration_id;

    public Integrations(String integration_id) {
        this.integration_id = integration_id;
    }

    public Object authorize(String account, String secret, String code) throws IOException, InterruptedException {
        String url = "/integration/" + account + "/authorize";

        JSONObject payload = new JSONObject();
        payload.put("secret", secret);
        payload.put("code", code);

        return utils._request_without_token("POST", url, payload);
    }

    public Object refresh_token(String account, String token, String refresh_token) throws IOException, InterruptedException {
        String url = "/integration/" + account + "/refresh_token";

        JSONObject payload = new JSONObject();
        payload.put("token", token);
        payload.put("refresh_token", refresh_token);

        return utils._request_without_token("POST", url, payload);
    }
}
