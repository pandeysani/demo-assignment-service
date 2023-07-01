package com.assignment.demo.controller;

import com.assignment.demo.service.AssignmentService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.URL;

@RestController
@RequestMapping(value="/assignment")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;


    @GetMapping( value = "/getGeoObject", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<JsonObject> getGeoIP()  {
        return new ResponseEntity<>(assignmentService.getGeoIP(), HttpStatus.OK);
    }
    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<String> getGeoIPCsv() throws IOException {
        JsonObject data = assignmentService.getGeoIP();
        String csvData = convertJsonToCsv(data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "geoip.csv");
        return ResponseEntity.ok()
                    .headers(headers)
                    .body(csvData);
    }

    private String convertJsonToCsv(JsonObject jsonObject) throws IOException {
        Writer writer = new StringWriter();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(jsonObject.keySet().toArray(new String[0]));
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            csvPrinter.printRecord(jsonObject.values());
            csvPrinter.flush();
            FileOutputStream outputStream = new FileOutputStream("text.csv");
            outputStream.write(writer.toString().getBytes());
            outputStream.close();
        }
        return writer.toString();
    }
}
