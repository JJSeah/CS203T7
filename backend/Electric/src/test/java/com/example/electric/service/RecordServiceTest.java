package com.example.electric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.electric.model.*;
import com.example.electric.model.Record;
import com.example.electric.respository.RecordRepository;


@ExtendWith(MockitoExtension.class) public class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordService recordService;

    //fail why ah?
    @Test
    void testCreateRecord() {
        Record record = new Record((long) 1);
        Record result = recordRepository.save(record);
        assertEquals(record, result);
    }

    @Test
    public void testDeleteRecord() {
        Long idToDelete = 1L;
        recordService.deleteRecord(idToDelete);
        verify(recordRepository).deleteById(idToDelete);
    }

    @Test
    public void testGetAllRecords() {
        List<Record> expectedRecords = new ArrayList<>();
        expectedRecords.add(new Record(1L));
        expectedRecords.add(new Record(2L));
        when(recordRepository.findAll()).thenReturn(expectedRecords);
        List<Record> actualRecords = recordService.getAllRecords();
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    public void testGetAllRecordsByUser_Exist() {
        Long userId = 1L;
        User user = new User(userId, "john");
        List<Record> expectedRecords = new ArrayList<>();
        expectedRecords.add(new Record(1L, user));
        expectedRecords.add(new Record(2L, user));

        when(recordRepository.findRecordsByUserId(userId)).thenReturn(expectedRecords);

        List<Record> actualRecords = recordService.getAllRecordsByUser(userId);

        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    public void testGetAllRecordsByUser_NotExist() {
        List<Record> expectedRecords = new ArrayList<>();
        when(recordRepository.findRecordsByUserId(anyLong())).thenReturn(expectedRecords);
        List<Record> actualRecords = recordService.getAllRecordsByUser(anyLong());
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    public void testGetRecordById_RecordExists() {
        Long idToFind = 1L;
        Record expectedRecord = new Record(1L);
        Optional<Record> optionalRecord = Optional.of(expectedRecord);

        when(recordRepository.findById(idToFind)).thenReturn(optionalRecord);

        Record actualRecord = recordService.getRecordById(idToFind);

        assertEquals(expectedRecord, actualRecord);
        verify(recordRepository).findById(idToFind);
    }

    @Test
    public void testGetRecordById_RecordNotExists() {
        Long idToFind = 2L;
        Optional<Record> optionalRecord = Optional.empty();

        when(recordRepository.findById(idToFind)).thenReturn(optionalRecord);

        Record actualRecord = recordService.getRecordById(idToFind);

        assertNull(actualRecord);
        verify(recordRepository).findById(idToFind);

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

    //How to check if if statment ran
    @Test
    public void testUpdateRecord_RecordExists() {
        Long idToUpdate = 1L;
        User user = new User();

        Record updatedRecord = new Record(1L, user);
        Record existingRecord = new Record(1L);
        Optional optionalExistingRecord = Optional.of(existingRecord);

        when(recordRepository.findById(anyLong())).thenReturn(optionalExistingRecord);
        when(recordRepository.save(existingRecord)).thenReturn(updatedRecord);

        Record actualRecord = recordService.updateRecord(idToUpdate, updatedRecord);

        // Then verify that the returned record matches the updated record
        // How to check if the if statement was true or false
        assertEquals(updatedRecord, actualRecord);
        verify(recordRepository).findById(idToUpdate);
        // verify(existingRecord).setUser(user);
        verify(recordRepository).save(updatedRecord);

    }

    @Test
    public void testUpdateRecord_RecordNotExists() {
        // Given a record ID 
        Long idToUpdate = 2L;
        Record updatedRecord = new Record(2L, new User());

        // Stub 
        when(recordRepository.findById(anyLong())).thenReturn(Optional.empty());
        // when(Optional.empty().isPresent()).thenReturn(false);

        Record actualRecord = recordService.updateRecord(idToUpdate, updatedRecord);
        
        assertNull(actualRecord);
        // verify(Optional.empty(), null).isPresent();
    }

}
