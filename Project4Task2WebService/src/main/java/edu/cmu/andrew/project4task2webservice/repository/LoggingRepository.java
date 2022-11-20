package edu.cmu.andrew.project4task2webservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import edu.cmu.andrew.project4task2webservice.model.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.combine;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class LoggingRepository {
    private static String DATABASE = "DsProject4Task2DB";

    private static String COLLECTION = "GIFBotLogEvent";

    private MongoDatabase database;

    public LoggingRepository() {
        this.database = MongoDBClient.getInstance().getClient().getDatabase(DATABASE);
    }

    public Document upsert(LogEvent logEvent) {
        Bson filter = Filters.eq("_id", logEvent.getRequestID());
        FindOneAndUpdateOptions upsertOptions = new FindOneAndUpdateOptions();
        upsertOptions.returnDocument(ReturnDocument.AFTER);
        upsertOptions.upsert(true);
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

        Bson clientResponseCollection = project(fields(Projections.excludeId(), include(
                "client_response")));

        getCollection().aggregate(
                Arrays.asList(
                        clientResponseCollection,
                        Aggregates.unwind("$client_response.responseBody.gifs"),
                        group("$client_response.responseBody.gifs", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            topGIFs.add(document.get("_id").toString());
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
        getCollection().aggregate(
                Arrays.asList(
                        group("$client_request.searchTerm", Accumulators.sum("count", 1)),
                        sort(Sorts.descending("count")),
                        limit(count)
                )
        ).forEach(document -> {
            topSearchWords.add(document.get("_id").toString());
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
            latency.setAverage(Double.valueOf(document.get("latencyAvg").toString()));
            latency.setMaximum(Double.valueOf(document.get("latencyMax").toString()));
            latency.setMinimum(Double.valueOf(document.get("latencyMin").toString()));
        }


        getCollection().find().projection(fields(include("service_latency"))).sort(new BasicDBObject("created_at",
                -1)).limit(count).forEach(doc -> {
            latency.getRecentRecords().add(Double.valueOf(doc.get("service_latency").toString()));
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
            latency.setAverage(Double.valueOf(document.get("latencyAvg").toString()));
            latency.setMaximum(Double.valueOf(document.get("latencyMax").toString()));
            latency.setMinimum(Double.valueOf(document.get("latencyMin").toString()));
        }

        getCollection().find().projection(fields(include("external_api_latency"))).sort(new BasicDBObject("created_at",
                -1)).limit(count).forEach(doc -> {
            latency.getRecentRecords().add(Double.valueOf(doc.get("external_api_latency").toString()));
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
            deviceInfo.getManufacture().add(document.get("_id").toString());
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
            deviceInfo.getBrand().add(document.get("_id").toString());
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
            deviceInfo.getModel().add(document.get("_id").toString());
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
            deviceInfo.getAndroidVersion().add(document.get("_id").toString());
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
            SystemLog systemLog = new SystemLog(
                    document.get("client_request").toString(),
                    document.get("client_response").toString(),
                    document.get("client_device").toString(),
                    document.get("external_api_request").toString(),
                    document.get("external_api_response").toString()
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
