package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.LogEvent;
import edu.cmu.andrew.project4task2webservice.repository.LoggingRepository;
import org.bson.Document;

public class LoggingService {
    private LoggingRepository repository;

    public LoggingService() {
        this.repository = new LoggingRepository();
    }

    public Document save(LogEvent logEvent) {
        return repository.upsert(logEvent);
    }


}
