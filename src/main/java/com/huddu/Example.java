package com.huddu;

import org.json.JSONObject;

import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException, InterruptedException {

        // An example:

        ApiClient cl =new ApiClient("5f9acfe02dcc603e674edddc8e6bbba1");
        Object res = cl.Accounts.list_installations(0,25);
        System.out.println(res);
    }
}