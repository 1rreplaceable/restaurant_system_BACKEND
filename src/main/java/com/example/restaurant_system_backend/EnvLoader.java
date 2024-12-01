package com.example.restaurant_system_backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EnvLoader {

    public static Properties loadEnv() {
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get(".env")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load .env file", e);
        }
        return properties;
    }
}