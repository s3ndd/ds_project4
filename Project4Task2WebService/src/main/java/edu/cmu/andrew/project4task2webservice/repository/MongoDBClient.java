/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for Mongo database client.
 *
 */

package edu.cmu.andrew.project4task2webservice.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBClient {
    //mongodb connecting string
    private final static String CONNECTION_STRING = "mongodb+srv://dsteamproject4:JyHsGiSpAuU44rrL@cluster0.y3xuxkb" +
            ".mongodb.net/?retryWrites=true&w=majority";

    //create instance of MongoDBClient
    private static MongoDBClient instance = null;

    //create MongoClient object
    private final MongoClient client;

    //constructor of MongoDBClient
    private MongoDBClient() {
        client = MongoClients.create(CONNECTION_STRING);
    }

    //get instance of MongoDBClient
    public static synchronized MongoDBClient getInstance() {
        // check if MongoClient instance is null, then create new one
        if (instance == null) {
            instance = new MongoDBClient();
        }
        return instance;
    }

    //get MongoClient object
    public MongoClient getClient() {
        return client;
    }
}
