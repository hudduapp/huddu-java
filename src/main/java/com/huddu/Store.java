package com.huddu;

import com.huddu._exceptions.APIException;
import com.huddu._exceptions.SafePutException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Store {
    private final _sessions session;
    private final _responses responses = new _responses();

    public Store(String token, String collection, String region) throws APIException {
        session = new _sessions(
                collection, token, region
        );

        session.setType("store");
    }

    public Store(String token, String collection, String region, String baseUrl) throws APIException {
        session = new _sessions(
                collection, token, region, baseUrl
        );

        session.setType("store");
    }


    public void put(String id, Object data, boolean safe) throws SafePutException {
        if (safe) {
            try {
                get(id);
                throw new SafePutException("");
            } catch (Exception e) {
                if (e.getClass().getName() == "com.huddu._exceptions.SafePutException") {
                    throw new SafePutException("Another entry with the same id already exists");
                }
            }

        }

        JSONArray body = new JSONArray();
        body.put(
                new JSONObject().put("id", id).put("data", data)
        );
        session.create_documents(body);

    }

    public void put(String id, Object data) throws SafePutException {
        put(id, data, true);
    }


    public Object get(String id, int start, int end) {
        ArrayList ids = new ArrayList();
        ids.add(id);

        return responses.makeResponse((JSONArray) session.list_documents(ids, 1, 0, start, end).get("data")).get(0);
    }


    public void update(String id, Object data) {
        JSONArray body = new JSONArray();
        body.put(
                new JSONObject().put("id", id).put("data", data)
        );
        session.create_documents(body);
    }

    public void delete(String id) {
        ArrayList ids = new ArrayList();
        ids.add(id);

        session.delete_documents(ids);
    }

    public Object get(String id) {
        ArrayList ids = new ArrayList();
        ids.add(id);

        return responses.makeResponse((JSONArray) session.list_documents(ids, 1, 0).get("data")).get(0);
    }


    public Object fetch(ArrayList ids, int skip, int limit, int start, int end) {
        return responses.makeResponse((JSONArray) session.list_documents(ids, limit, skip, start, end).get("data"));
    }

    public Object fetch(ArrayList ids, int skip, int limit) {
        return responses.makeResponse((JSONArray) session.list_documents(ids, limit, skip).get("data"));
    }


}
