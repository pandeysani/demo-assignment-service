package com.assignment.demo.util;

import com.assignment.demo.model.Location;
import org.springframework.expression.spel.ast.Literal;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface CSVFileService {
    ByteArrayInputStream getFileData(Location location) throws RuntimeException;
}
