package com.huddu;


import org.json.JSONArray;
import org.json.JSONObject;

public class _responses {

    public JSONArray makeResponse(JSONArray items) {
        JSONArray res = new JSONArray();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = (JSONObject) items.get(i);
            res.put(
                    item.get("data")
            );
        }
        return res;
    }

}
