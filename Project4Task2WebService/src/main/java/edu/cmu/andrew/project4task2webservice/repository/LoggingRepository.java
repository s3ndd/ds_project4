/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for logging repository(Mongo database).
 */

package edu.cmu.andrew.project4task2webservice.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import edu.cmu.andrew.project4task2webservice.model.Latency;
import edu.cmu.andrew.project4task2webservice.model.LogEvent;
import edu.cmu.andrew.project4task2webservice.model.SystemLog;
import edu.cmu.andrew.project4task2webservice.model.TopDeviceInfo;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.combine;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class LoggingRepository {
    // define the database name
    private static String DATABASE = "DsProject4Task2DB";

    // define the collection name
    private static String COLLECTION = "GIFBotLogEvent";

    // declare a MongoDatabase variable
    private MongoDatabase database;

    // initialize the LoggingRepository
    public LoggingRepository() {
        this.database = MongoDBClient.getInstance().getClient().getDatabase(DATABASE);
    }

    /**
     * upsert the logEvent data to the MongoDB
     *
     * @param logEvent
     * @return
     */
    public Document upsert(LogEvent logEvent) {
        // define the filer with _id
        Bson filter = Filters.eq("_id", logEvent.getRequestID());
        // set up the find and update option
        FindOneAndUpdateOptions upsertOptions = new FindOneAndUpdateOptions();
        upsertOptions.returnDocument(ReturnDocument.AFTER);
        upsertOptions.upsert(true);
        // do the inert or update
        return getCollection().findOneAndUpdate(filter, toBson(logEvent), upsertOptions);
    }

    /**
     * read the top 20 returned gifs
     * db.GIFBotLogEvent.aggregate([
     * {
     * $project:{
     * client_response:1
     * }}
     * ,
     * { $unwind: "$client_response.responseBody.gifs" },
     * {
     * $group:{
     * _id:"$client_response.responseBody.gifs",
     * count:{$sum:1}
     * }
     * },
     * { $sort : { count: -1 } },
     * {$limit: 20}]
     * )
     *
     * @return
     */
    public List<String> getTopGIFs(int count) {
        List<String> topGIFs = new ArrayList<>();
        // define the project
        Bson clientResponseCollection = project(fields(Projections.excludeId(), include(
                "client_response")));
        // run the aggregate query
        getCollection().aggregate(
                Arrays.asList(
                        clientResponseCollection,
                        Aggregates.unwind("$client_response.responseBody.gifs"),
                        group("$client_response.responseBody.gifs", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            // add the gif url to the list
            topGIFs.add(document.get("_id") != null ? document.get("_id").toString() : "");
        });
        return topGIFs;
    }

    /**
     * read the top 20 search term
     * db.GIFBotLogEvent.aggregate([
     * { $ group:{ _id:" $ client_request.searchTerm", count:{ $ sum:1} } },
     * { $ sort : { count: - 1 } },
     * { $ limit: 20}
     * ])
     *
     * @return a list of top search word
     */
    public List<String> getTopSearchWords(int count) {
        List<String> topSearchWords = new ArrayList<>();
        // run the aggregate query
        getCollection().aggregate(
                Arrays.asList(
                        group("$client_request.searchTerm", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            // add the word to the list
            topSearchWords.add(document.get("_id") != null ? document.get("_id").toString() : "");
        });
        return topSearchWords;
    }

    /**
     * get the service latency statistics
     * db.GIFBotLogEvent.aggregate([
     * { $sort : { "created_at": -1 } },
     * {$limit:100},
     * {
     * $group:{
     * _id: null,
     * latencyAvg: { $avg: "$service_latency"},
     * latencyMax: {$max: "$service_latency"},
     * latencyMin: {$min: "$service_latency"}
     * }
     * }
     * ])
     *
     * @return a Latency object
     */
    public Latency getServiceLatency(int count) {
        // run the aggregate query
        Document document = getCollection().aggregate(
                Arrays.asList(
                        group(null,
                                Accumulators.avg("latencyAvg", "$service_latency"),
                                Accumulators.max("latencyMax", "$service_latency"),
                                Accumulators.min("latencyMin", "$service_latency"))

                )
        ).first();
        Latency latency = new Latency();
        if (document != null) {
            try {
                // write the latency data to the object
                latency.setAverage(Double.valueOf(document.get("latencyAvg").toString()));
                latency.setMaximum(Double.valueOf(document.get("latencyMax").toString()));
                latency.setMinimum(Double.valueOf(document.get("latencyMin").toString()));
            } catch (Exception e) {
                latency.setAverage(0.0);
                latency.setMaximum(0.0);
                latency.setMinimum(0.0);
            }
        }

        // define the project
        getCollection().find().projection(fields(include("service_latency"))).sort(new BasicDBObject("created_at",
                -1)).limit(count).forEach(doc -> {
            Object obj = doc.get("service_latency");
            if (obj != null) {
                // add the record to the list
                latency.getRecentRecords().add(Double.valueOf(obj.toString()));
            }
        });

        return latency;
    }

    /**
     * get the external API latency statistics
     * db.GIFBotLogEvent.aggregate([
     * { $sort : { "created_at": -1 } },
     * {$limit:100},
     * {
     * $group:{
     * _id: null,
     * latencyAvg: { $avg: "$external_api_latency"},
     * latencyMax: {$max: "$external_api_latency"},
     * latencyMin: {$min: "$external_api_latency"}
     * }
     * }
     * ])
     *
     * @return a Latency object
     */
    public Latency getExternalAPILatency(int count) {
        // run the aggregate query
        Document document = getCollection().aggregate(
                Arrays.asList(
                        group(null,
                                Accumulators.avg("latencyAvg", "$external_api_latency"),
                                Accumulators.max("latencyMax", "$external_api_latency"),
                                Accumulators.min("latencyMin", "$external_api_latency"))

                )
        ).first();
        Latency latency = new Latency();
        if (document != null) {
            // add the latency data
            try {
                latency.setAverage(Double.valueOf(document.get("latencyAvg").toString()));
                latency.setMaximum(Double.valueOf(document.get("latencyMax").toString()));
                latency.setMinimum(Double.valueOf(document.get("latencyMin").toString()));
            } catch (Exception e) {
                latency.setAverage(0.0);
                latency.setMaximum(0.0);
                latency.setMinimum(0.0);
            }

        }

        // define the project
        getCollection().find().projection(fields(include("external_api_latency"))).sort(new BasicDBObject("created_at",
                -1)).limit(count).forEach(doc -> {
            Object obj = doc.get("external_api_latency");
            if (obj != null) {
                // add the latency data to the list
                latency.getRecentRecords().add(Double.valueOf(obj.toString()));
            }
        });
        return latency;
    }

    /**
     * manufacture:
     * db.GIFBotLogEvent.aggregate([{
     * $group:{
     * _id:"$client_device.manufacture",
     * count:{$sum:1}
     * }
     * },
     * { $sort : { count: -1 } },
     * {$limit: 10}])
     * <p>
     * brand:
     * db.GIFBotLogEvent.aggregate([{
     * $group:{
     * _id:"$client_device.brand",
     * count:{$sum:1}
     * }
     * },
     * { $sort : { count: -1 } },
     * {$limit: 10}])
     * <p>
     * model:
     * db.GIFBotLogEvent.aggregate([{
     * $group:{
     * _id:"$client_device.model",
     * count:{$sum:1}
     * }
     * },
     * { $sort : { count: -1 } },
     * {$limit: 5}])
     * <p>
     * androidVersion:
     * db.GIFBotLogEvent.aggregate([{
     * $group:{
     * _id:"$client_device.androidVersion",
     * count:{$sum:1}
     * }
     * },
     * { $sort : { count: -1 } },
     * {$limit: 10}])
     *
     * @return
     */
    public TopDeviceInfo getTopDevices(int count) {
        TopDeviceInfo deviceInfo = new TopDeviceInfo();
        /*
         db.GIFBotLogEvent.aggregate([{
         $group:{
         _id:"$client_device.manufacture",
         count:{$sum:1}
         }
         },
         { $sort : { count: -1 } },
         {$limit: 10}])
         */
        getCollection().aggregate(
                Arrays.asList(
                        group("$client_device.manufacture", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            deviceInfo.getManufacture().add(document.get("_id") != null ? document.get("_id").toString() : "");
        });

        /*
         brand:
         db.GIFBotLogEvent.aggregate([{
         $group:{
         _id:"$client_device.brand",
         count:{$sum:1}
         */
        getCollection().aggregate(
                Arrays.asList(
                        group("$client_device.brand", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            deviceInfo.getBrand().add(document.get("_id") != null ? document.get("_id").toString() : "");
        });

        /*
         model:
         db.GIFBotLogEvent.aggregate([{
         $group:{
         _id:"$client_device.model",
         count:{$sum:1}
         }
         },
         { $sort : { count: -1 } },
         {$limit: 5}])
        */
        getCollection().aggregate(
                Arrays.asList(
                        group("$client_device.model", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            deviceInfo.getModel().add(document.get("_id") != null ? document.get("_id").toString() : "");
        });
        /*
         androidVersion:
         db.GIFBotLogEvent.aggregate([{
         $group:{
         _id:"$client_device.androidVersion",
         count:{$sum:1}
         }
         },
         { $sort : { count: -1 } },
         {$limit: 10}])
         */
        getCollection().aggregate(
                Arrays.asList(
                        group("$client_device.androidVersion", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            deviceInfo.getAndroidVersion().add(document.get("_id") != null ? document.get("_id").toString() : "");
        });
        return deviceInfo;
    }

    /**
     * retrieve all saved logs and formatted.
     * db.GIFBotLogEvent.find({}).sort({ "created_at": -1 }).limit(2)
     */
    public List<SystemLog> getFormattedLogs(int count) {
        List<SystemLog> logs = new ArrayList<>();
        getCollection().find().sort(new BasicDBObject("created_at", -1)).limit(count).forEach(document -> {
            // public SystemLog(String clientRequest, String clientResponse, String clientDevice, String
            // externalAPIRequest, String externalAPIResponse)
            String clientRequest = document.get("client_request") != null ?
                    document.get("client_request").toString() : "";
            String clientResponse = document.get("client_response") != null ?
                    document.get("client_response").toString() : "";
            String clientDevice = document.get("client_device") != null ? document.get("client_device").toString() : "";
            String apiRequest = document.get("external_api_request") != null ?
                    document.get("external_api_request").toString() : "";
            String apiResponse = document.get("external_api_response") != null ?
                    document.get("external_api_response").toString() : "";

            SystemLog systemLog = new SystemLog(
                    clientRequest,
                    clientResponse,
                    clientDevice,
                    apiRequest,
                    apiResponse
            );
            logs.add(systemLog);
        });

        return logs;
    }

    private MongoCollection<Document> getCollection() {
        CodecRegistry pojoCodecRegistry =
                fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return MongoDBClient.getInstance().getClient().getDatabase(DATABASE).withCodecRegistry(pojoCodecRegistry).getCollection(COLLECTION);
    }

    private static Bson toBson(LogEvent logEvent) {
        List<Bson> updates = new ArrayList<>();
        updates.add(Updates.set("_id", logEvent.getRequestID()));
        updates.add(Updates.set("created_at", new Date()));
        if (logEvent.getClientRequestInfo() != null) {
            updates.add(Updates.set("client_request", logEvent.getClientRequestInfo()));
        }
        if (logEvent.getClientResponseInfo() != null) {
            updates.add(Updates.set("client_response", logEvent.getClientResponseInfo()));

        }

        if (logEvent.getRequestDeviceInfo() != null) {
            updates.add(Updates.set("client_device", logEvent.getRequestDeviceInfo()));
        }

        if (logEvent.getServiceLatency() != null) {
            updates.add(Updates.set("service_latency", logEvent.getServiceLatency()));
        }

        if (logEvent.getTenorRequestInfo() != null) {
            updates.add(Updates.set("external_api_request", logEvent.getTenorRequestInfo()));
        }
        if (logEvent.getTenorResponseInfo() != null) {
            updates.add(Updates.set("external_api_response", logEvent.getTenorResponseInfo()));
        }

        if (logEvent.getTenorAPILatency() != null) {
            updates.add(Updates.set("external_api_latency", logEvent.getTenorAPILatency()));
        }

        return combine(updates);
    }

}
