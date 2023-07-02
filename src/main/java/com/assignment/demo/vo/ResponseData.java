package com.assignment.demo.vo;

import com.assignment.demo.model.Location;

public class ResponseData {
    private Location location;
    private String errorMessage;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
