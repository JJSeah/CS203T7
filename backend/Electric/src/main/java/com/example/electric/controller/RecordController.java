package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Record;
import com.example.electric.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/all")
    @Operation(summary = "Get All Records", description = "Get All Records",tags = {"Record"})
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Records", description = "Get Record by ID",tags = {"Record"})
    public Record getRecordById(@PathVariable Long id) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return recordService.getRecordById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get User's Records", description = "Get User's Records by UserID",tags = {"Record"})
    public List<Record> getAllRecordsByUser(@PathVariable Long userId) {
        return recordService.getAllRecordsByUser(userId);
    }

    @PostMapping
    @Operation(summary = "Create Records", description = "Create Records",tags = {"Record"})
    public Record createRecord(@RequestBody Record record) {
        return recordService.createRecord(record);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Records", description = "Update Records",tags = {"Record"})
    public Record updateRecord(@PathVariable Long id, @RequestBody Record updatedRecord) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return recordService.updateRecord(id, updatedRecord);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Records", description = "Delete Records",tags = {"Record"})
    public void deleteRecord(@PathVariable Long id) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        recordService.deleteRecord(id);
    }
}
