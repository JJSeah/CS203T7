package com.example.electric.controller;

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

    @GetMapping("v1/all")
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("v1/{id}")
    public Record getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    @PostMapping("v1/")
    public Record createRecord(@RequestBody Record record) {
        return recordService.createRecord(record);
    }

    @PutMapping("v1/{id}")
    public Record updateRecord(@PathVariable Long id, @RequestBody Record updatedRecord) {
        return recordService.updateRecord(id, updatedRecord);
    }

    @DeleteMapping("v1/{id}")
    public void deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
    }
}
