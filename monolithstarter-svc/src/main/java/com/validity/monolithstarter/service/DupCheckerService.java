package com.validity.monolithstarter.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.validity.monolithstarter.domain.Record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DupCheckerService {

    /**
     * added test-files to access relative path to read CSV.
     */
    public static final String filePath = "../test-files/normal.csv";
    
    private static final Logger log = LoggerFactory.getLogger(DupCheckerService.class);

    /**
     * This method reads CSV and parses each row
     * Adds Record for each row and create a list
     * of records
     * @param filePath
     * @return list of records
     */
    static List<Record> readCSV(String filePath) {

        List<Record> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                Record record = new Record(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4],
                 row[5], row[6], row[7], row[8], row[9], row[10], row[11]);

                data.add(record);
            }
        } catch (IOException ioException) {
            log.error("IO Exception: " + ioException);
        }

        return data;
    }

    /**
     * This method implements Levenshtein Distance algorithm
     * using dynamic programming approach.
     * @param string1
     * @param string2
     * @return int value of distance between string1 and string2
     */
    static int getLevenshteinDistance(String string1, String string2) {

        int n = string1.length();
        int m = string2.length();
        int[][] distance = new int[n + 1][m + 1];

        for (int index = 0; index <= n; index++) {
            distance[index][0] = index;
        }
        for (int index = 0; index <= m; index++) {
            distance[0][index] = index;
        }

        int substitutionCost;
        for (int index1 = 1; index1 <= n; index1++) {
            for (int index2 = 1; index2 <= m; index2++) {
                if (string1.charAt(index1 - 1) == string2.charAt(index2 - 1)) {
                    substitutionCost = 0;
                } else {
                    substitutionCost = 1;
                }
                distance[index1][index2] = Math.min(
                        Math.min(distance[index1][index2 - 1] + 1, distance[index1 - 1][index2] + 1),
                        distance[index1 - 1][index2 - 1] + substitutionCost);
            }
        }
        return distance[n][m];
    }

    /**
     * This method refines data and separates duplicate records from 
     * non-duplicate records.
     * @return JSON String object containing duplicates and non-duplicates list
     */
    public String getRefinedData() {
        
        List<Record> data = readCSV(filePath);
        Map<Integer, Record> duplicates = new HashMap<>();
        Set<Record> duplicateSet = new HashSet<>();
        Map<Integer, Record> nonDuplicates = new HashMap<>();

        for (int index1 = 0; index1 < data.size() - 1; index1++) {
            Record record1 = data.get(index1);
            boolean isNotDuplicate = true;

            for (int index2 = index1 + 1; index2 < data.size(); index2++) {

                Record record2 = data.get(index2);

                if (getLevenshteinDistance(record1.getFirst_name(), record2.getFirst_name()) < 3 
                    && getLevenshteinDistance(record1.getLast_name(), record2.getLast_name()) < 3
                    && getLevenshteinDistance(record1.getEmail(), record2.getEmail()) < 3) {
                    isNotDuplicate = false;
                    duplicateSet.add(record1);
                    duplicateSet.add(record2);
                    duplicates.put(record1.getId(), record1);
                    duplicates.put(record2.getId(), record2);
                }
            }
            if (isNotDuplicate && !nonDuplicates.containsKey(record1.getId()) && !duplicates.containsKey(record1.getId())) {
                nonDuplicates.put(record1.getId(), record1);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String dups = "";
        String nonDups = "";
        
        try {
            dups = mapper.writeValueAsString(duplicateSet);
            nonDups = mapper.writeValueAsString(nonDuplicates.values());
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("JsonProcessing Exception: " + jsonProcessingException);
        }

        String jsonString = "{ \"Duplicates\" : " + dups + "," + "\"Non-Duplicates\" : " + nonDups + "}";
        
        return jsonString;
    }
}
