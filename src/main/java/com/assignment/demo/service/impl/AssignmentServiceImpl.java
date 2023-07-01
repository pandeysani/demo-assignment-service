package com.assignment.demo.service.impl;

import com.assignment.demo.service.AssignmentService;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Override
    public JsonObject getGeoIP()  {
        URL url = null;
        JsonReader reader=null;
        try {
            url = new URL("https://freegeoip.app/json/");
            reader =Json.createReader(new InputStreamReader(url.openStream()));
            return reader.readObject();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
