package com.assignment.demo.service;

import com.assignment.demo.model.Location;

import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface AssignmentService {
     JsonObject getGeoIP() throws RuntimeException;
     Location getGeoData() throws RuntimeException;
     ByteArrayInputStream getLocationCSVInputStream() throws RuntimeException;

}
