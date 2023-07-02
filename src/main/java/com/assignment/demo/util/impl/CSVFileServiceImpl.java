package com.assignment.demo.util.impl;

import com.assignment.demo.model.Location;
import com.assignment.demo.util.CSVFileService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVFileServiceImpl implements CSVFileService {

    private final static Logger log = LogManager.getLogger(CSVFileServiceImpl.class);

    @Override
    public ByteArrayInputStream getFileData(Location location) throws RuntimeException {
        // Headers for CSV file
        String [] headers = {"IP", "Country Code", "Country Name", "Region Code", "Region Name", "City", "Zip Code",
                "Time Zone", "Latitude", "Longitude", "Metro Code"};
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headers);
        // CSV Data
        List<String> csvData =  Arrays.asList(location.getIp(),
                    location.getCountry_code(),
                    location.getCountry_name(),
                    location.getRegion_code(),
                    location.getRegion_name(),
                    location.getCity(),
                    location.getZip_code(),
                    location.getTime_zone(),
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getMetro_code());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = null;
        try {
            csvPrinter = new CSVPrinter(new PrintWriter(out), csvFormat);
            csvPrinter.printRecord(csvData);
            csvPrinter.flush();
            // Writing file to local system.
            FileOutputStream outputStream = new FileOutputStream(new File("location.csv"));
            outputStream.write(out.toByteArray());
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            log.error("Some Error occurred while preparing CSV file.", e);
            throw new RuntimeException("Some Error occurred while preparing CSV file.");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("Some Error occurred while closing the streams.", e);
                    throw new RuntimeException("Some Error occurred while closing the streams.");
                }
            }
            if(csvPrinter != null) {
                try {
                    csvPrinter.close();
                } catch (IOException e) {
                    log.error("Some Error occurred while closing the streams.", e);
                    throw new RuntimeException("Some Error occurred while closing the streams.");
                }
            }
        }
    }
}
