package com.app.interview.domain.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TsvFileReader {
    private static final Logger LOGGER = LogManager.getLogger(TsvFileReader.class);

    private final String filePath;

    public TsvFileReader(String filePath) {
        this.filePath = filePath;
    }

    public void readTsvFile(Task task, String fileName) {
        try (InputStream inputStream = new FileSystemResource(filePath + fileName).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split("\t");
                task.execute(columns);
            }

        } catch (IOException e) {
            LOGGER.error("Failed to read file: {}", fileName, e);
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface Task {
        void execute(String[] columns);
    }
}