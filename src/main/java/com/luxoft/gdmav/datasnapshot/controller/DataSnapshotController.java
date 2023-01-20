package com.luxoft.gdmav.datasnapshot.controller;

import com.luxoft.gdmav.datasnapshot.entity.DataSnapshot;
import com.luxoft.gdmav.datasnapshot.service.DataSnapshotService;
import com.luxoft.gdmav.datasnapshot.utils.CSVHelper;
import com.luxoft.gdmav.datasnapshot.utils.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class DataSnapshotController {
    @Autowired
    DataSnapshotService dataSnapshotService;

    @PostMapping("/dataSnapshot/upload")
    public ResponseEntity<CustomMessage> uploadCsvDataSnapshots( @RequestParam("file") MultipartFile file){
        log.info("controller: uploading csv data snapshots  from file " +file.getContentType() );
        if (!CSVHelper.hasCSVFormat(file)) {
            try {
                dataSnapshotService.uploadCsv(file);
                return new ResponseEntity(CustomMessage.builder()
                        .message(file.getOriginalFilename() + " file uploaded successfully !")
                        .build(), HttpStatus.OK);
            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body( CustomMessage.builder()
                                .message("Could not upload the file: " + file.getOriginalFilename() + "!")
                                .build()
                        );
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomMessage("Please upload a csv file! bad file type:"+file.getOriginalFilename()));
    }



    @GetMapping("/dataSnapshot/{id}")
    DataSnapshot getDataSnapshot (@PathVariable String id){
        log.info(" getting datasnapshot with id " +id);
        return dataSnapshotService.get(Long.parseLong(id)).get();
    }

    @DeleteMapping("/dataSnapshot/{id}")
    void  deleteDataSnapshot (@PathVariable Long id){
        log.info("deleting dataSnapshot with id " +id);
        dataSnapshotService.delete(id);

    }

}
