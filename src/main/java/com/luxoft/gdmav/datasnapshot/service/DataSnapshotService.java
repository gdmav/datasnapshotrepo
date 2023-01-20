package com.luxoft.gdmav.datasnapshot.service;

import com.luxoft.gdmav.datasnapshot.entity.DataSnapshot;
import com.luxoft.gdmav.datasnapshot.repository.DataSnapshotRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DataSnapshotService {
    @Autowired
    DataSnapshotRepository dataSnapshotRepository;

    public Optional<DataSnapshot>  get (Long id ){
        log.info("get method called ");
        return Optional.of(dataSnapshotRepository.getById(id))
                .or(()->Optional.empty());

    }
    public  void delete (Long id ){
        log.info("deleting record  "+id);
        dataSnapshotRepository.deleteById(id);
    }

    public void uploadCsv(MultipartFile file) {

        log.info("uploading csv records ");
        try {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF8"));
            CSVParser csvParser = new CSVParser(bufferedReader,
                    CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withIgnoreEmptyLines()
                            .withIgnoreHeaderCase()
                            .withTrim()
            );
            Iterable<CSVRecord>  csvRecords = csvParser.getRecords();
            for(CSVRecord record : csvRecords){
                DataSnapshot dataSnapshot = new DataSnapshot(
                        Long.parseLong(record.get("primary_key")),
                        record.get("name"),  //[description, name, primary_key, updated_timestamp]"
                        record.get("description"),
                       "test"
                        //Date.(record.get("updated_timestamp"))


                );
                //DataSnapshot.builder().id().name().description().updatedTimeStamp().build();
                dataSnapshotRepository.save(dataSnapshot);
                log.info("saved data "+dataSnapshot);

            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}