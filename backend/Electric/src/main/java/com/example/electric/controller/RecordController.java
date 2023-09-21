package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Record;
import com.example.electric.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * Retrieve a list of all records.
     *
     * This endpoint retrieves a list of all records currently stored in the system.
     * If no records are available, an empty list will be returned.
     *
     * @return A list of records, which may be empty if no records are found.
     */
    @GetMapping("/all")
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    /**
     * Retrieve a record by its unique identifier.
     *
     * This endpoint retrieves a record from the system based on its unique identifier (ID).
     * If a record with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the record to retrieve.
     * @return The retrieved record.
     * @throws ObjectNotFoundException If no record with the given ID is found.
     */
    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable Long id) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return recordService.getRecordById(id);
    }

    /**
     * Create a new record in the system.
     *
     * This endpoint allows the creation of a new record in the system. The provided
     * record object should contain the necessary details for creating the record.
     *
     * @param record The record object to be created.
     * @return The newly created record.
     */
    @PostMapping
    public Record createRecord(@RequestBody Record record) {
        return recordService.createRecord(record);
    }

    /**
     * Update an existing record with the provided information.
     *
     * This endpoint allows the update of an existing record identified by its unique
     * identifier (ID). The provided updatedRecord object should contain the updated details
     * for the record. If a record with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the record to update.
     * @param updatedRecord The updated record object containing new information.
     * @return The updated record.
     * @throws ObjectNotFoundException If no record with the given ID is found.
     */
    @PutMapping("/{id}")
    public Record updateRecord(@PathVariable Long id, @RequestBody Record updatedRecord) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return recordService.updateRecord(id, updatedRecord);
    }

    /**
     * Delete a record by its unique identifier.
     *
     * This endpoint allows the deletion of a record from the system based on its unique
     * identifier (ID). If a record with the specified ID is not found, it will result in an
     * ObjectNotFoundException.
     *
     * @param id The unique identifier of the record to delete.
     * @throws ObjectNotFoundException If no record with the given ID is found.
     */
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        recordService.deleteRecord(id);
    }
}
