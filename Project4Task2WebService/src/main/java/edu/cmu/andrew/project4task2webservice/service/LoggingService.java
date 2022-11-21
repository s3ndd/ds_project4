/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for logging service to save event log.
 */

package edu.cmu.andrew.project4task2webservice.service;

import edu.cmu.andrew.project4task2webservice.model.LogEvent;
import edu.cmu.andrew.project4task2webservice.repository.LoggingRepository;
import org.bson.Document;

public class LoggingService {
    // logging repository
    private LoggingRepository repository;

    // constructor of LoggingService
    public LoggingService() {
        this.repository = new LoggingRepository();
    }

    // save event log
    public Document save(LogEvent logEvent) {
        return repository.upsert(logEvent);
    }
}
