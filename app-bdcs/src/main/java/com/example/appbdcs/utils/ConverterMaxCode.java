package com.example.appbdcs.utils;

public class ConverterMaxCode {
    public static String generateNextId(String currentId) {
        if (currentId == null || !currentId.contains("-")) {
            throw new IllegalArgumentException("Invalid currentId format: " + currentId);
        }
        String prefix = currentId.split("-")[0];
        int number = Integer.parseInt(currentId.split("-")[1]);
        int nextNumber = number + 1;
        return String.format("%s-%04d", prefix, nextNumber);
    }
}
