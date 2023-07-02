package com.assignment.demo.service.impl;

import com.assignment.demo.model.Location;
import com.assignment.demo.service.AssignmentService;
import com.assignment.demo.util.CSVFileService;
import com.assignment.demo.util.impl.CSVFileServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final static Logger log = LogManager.getLogger(CSVFileServiceImpl.class);

    @Value("${app.geo.endpoint}")
    private String geoEndPoint;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CSVFileService csvFileService;

    @Override
    public JsonObject getGeoIP() throws RuntimeException {
        URL url = null;
        InputStream inputStream = null;
        JsonReader reader=null;
        try {
            url = new URL(geoEndPoint);
            inputStream = url.openStream();
            reader =Json.createReader(new InputStreamReader(inputStream));
            return reader.readObject();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Location getGeoData() {
        log.info(" Geo IP call is starting.... ");
        Location location = restTemplate.getForObject(geoEndPoint, Location.class);
        log.info(" Geo IP call is completed.... ");
        return location;
    }

    @Override
    public ByteArrayInputStream getLocationCSVInputStream() throws RuntimeException {
        log.info(" Geo IP call is starting.... ");
        Location location = restTemplate.getForObject(geoEndPoint, Location.class);
        log.info(" Geo IP call is completed.... ");
        return csvFileService.getFileData(location);
    }
}
