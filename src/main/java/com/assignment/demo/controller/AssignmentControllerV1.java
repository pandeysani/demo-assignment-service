package com.assignment.demo.controller;

import com.assignment.demo.service.AssignmentService;
import com.assignment.demo.vo.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/V1")
public class AssignmentControllerV1 {

    private final static Logger log = LogManager.getLogger(AssignmentControllerV1.class);
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping( value = "/data/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData> getGeoIP()  {
        ResponseEntity<ResponseData> result = null;
        try {
            ResponseData responseData = new ResponseData();
            responseData.setLocation(assignmentService.getGeoData());
            responseData.setErrorMessage("");
            result = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseData responseData = new ResponseData();
            responseData.setLocation(null);
            responseData.setErrorMessage(e.getMessage());
            result = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @GetMapping("/data/csv/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "location.csv";
        try {
            InputStreamResource file = new InputStreamResource(assignmentService.getLocationCSVInputStream());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        } catch (RuntimeException e) {
            log.error(" There is some issue while downloading the file.", e);
        }
        return null;
    }
}
