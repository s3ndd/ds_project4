package edu.cmu.andrew.mongodbdemo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class MongoDBDemo {
    public static void main(String[] args) {
//        "mongodb+srv://gifbot:uecGp9EB7jotYwct@cluster0" +".awr7ggu.mongodb.net/?retryWrites=true&w=majority"
        ConnectionString connectionString = new ConnectionString("mongodb+srv://dsteamproject4:Cmuds@4project@cluster0.wm5whov.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        BufferedReader typed = new BufferedReader(new InputStreamReader(System.in));
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("project4Task1DB");
            System.out.println("Connected to MongoDB: " + database.getName());
            MongoCollection<Document> task1Collection = database.getCollection("task1");
            while (true) {
                printMenu();
                int selection = Integer.parseInt(typed.readLine());
                switch (selection) {
                    case 0: {
                        System.out.println("Enter a new string to save:");
                        String input = typed.readLine();
                        // save the document to collection
                        Document document = new Document("_id", new ObjectId());
                        document.append("user_input", input);
                        task1Collection.insertOne(document);
                        break;
                    }
                    case 1: {
                        // retrieve all documents and print
                        System.out.println("Retrieving all documents of task1.");
                        FindIterable<Document> iterable = task1Collection.find();
                        iterable.forEach(System.out::println);
                        break;
                    }
                    case 2: {
                        System.out.println("Quitting....");
                        break;
                    }
                    default: {
                        System.out.println("Invalid input. Please enter 0 - 2.");
                    }
                }
                if (selection == 2) {
                    break;
                }
            }
            typed.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printMenu() {
        System.out.print("""
                0. Save new document.        
                1. Retrieve all documents.
                2. Exit.
                """);
    }
}
