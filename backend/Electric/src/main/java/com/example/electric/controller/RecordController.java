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

    @GetMapping("/all")
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable Long id) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return recordService.getRecordById(id);
    }

    @PostMapping
    public Record createRecord(@RequestBody Record record) {
        return recordService.createRecord(record);
    }

    @PutMapping("/{id}")
    public Record updateRecord(@PathVariable Long id, @RequestBody Record updatedRecord) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return recordService.updateRecord(id, updatedRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        if (recordService.getRecordById(id) == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        recordService.deleteRecord(id);
    }
}
