package edu.cmu.andrew.project4task2webservice.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import edu.cmu.andrew.project4task2webservice.model.DeviceInfo;
import edu.cmu.andrew.project4task2webservice.model.Latency;
import edu.cmu.andrew.project4task2webservice.model.LogEvent;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.*;

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
    public List<String> getTop20GIFs() {
        return null;
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
    public List<String> getTop20SearchWords() {
        return null;
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
    public Latency getServiceLatency() {
        return null;
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
    public Latency getExternalAPILatency() {
        return null;
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
    public DeviceInfo getTop10Devices() {
        return null;
    }

    /**
     * retrieve all saved logs and formatted.
     * db.GIFBotLogEvent.find({});
     *
     * @return
     */
    public List<String> getFormattedLogs() {
        return null;
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
