package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GenerateDTOforTesting {
    public static <T> T test1(String url, Class<T> className){
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                .newBuilder(URI.create(url))
                .build();
        HttpResponse<String> response=null;
        try {
            response=client.send(request,HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GsonBuilder builder=new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.fromJson(response.body(),className);
    }

//    public static int status(String url){
//        HttpClient client=HttpClient.newHttpClient();
//        HttpRequest request=HttpRequest
//                .newBuilder(URI.create(url))
//                .build();
//        HttpResponse<String> response=null;
//        try {
//            response=client.send(request,HttpResponse.BodyHandlers.ofString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return response.statusCode();
//    }
}
