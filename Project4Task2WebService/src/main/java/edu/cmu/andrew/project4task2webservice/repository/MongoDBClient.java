package edu.cmu.andrew.project4task2webservice.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBClient {
    private final static String CONNECTION_STRING = "mongodb+srv://dsteamproject4:JyHsGiSpAuU44rrL@cluster0.y3xuxkb" +
            ".mongodb.net/?retryWrites=true&w=majority";


    private static MongoDBClient instance = null;

    private final MongoClient client;

    private MongoDBClient() {
        client = MongoClients.create(CONNECTION_STRING);
    }

    public static synchronized MongoDBClient getInstance() {
        if (instance == null) {
            instance = new MongoDBClient();
        }
        return instance;
    }

    public MongoClient getClient() {
        return client;
    }
}
