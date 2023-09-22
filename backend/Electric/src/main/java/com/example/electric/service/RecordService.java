package com.example.electric.service;

import com.example.electric.model.Record;
import com.example.electric.respository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record getRecordById(Long id) {
        Optional<Record> optionalRecord = recordRepository.findById(id);
        return optionalRecord.orElse(null);
    }

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public Record updateRecord(Long id, Record updatedRecord) {
        Optional<Record> optionalRecord = recordRepository.findById(id);
        if (optionalRecord.isPresent()) {
            Record record = optionalRecord.get();
            // Update the record fields as needed
            record.setUser(updatedRecord.getUser());
            record.setCar(updatedRecord.getCar());
            record.setDuration(updatedRecord.getDuration());
            record.setCost(updatedRecord.getCost());
            record.setStation(updatedRecord.getStation());
            record.setStartTime(updatedRecord.getStartTime());
            record.setEndTime(updatedRecord.getEndTime());
            record.setDate(updatedRecord.getDate());
            return recordRepository.save(record);
        } else {
            return null; // Record not found
        }
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }

    public List<Record> getAllRecordsByUser(Long userId) {
        return recordRepository.findRecordsByUserId(userId);
    }
}
