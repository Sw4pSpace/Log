package com.sw4pspace.bukkit.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicBoolean;

public class Config {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File configFile;
    private JsonObject config;

    private static AtomicBoolean logToConsole = new AtomicBoolean(false);

    public void load(File dataFolder) {

        configFile = new File(dataFolder, "config.json");

        if(!configFile.exists()) {
            try {
                StringBuilder contents = new StringBuilder();
                try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json")) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    reader.lines().forEach(line -> contents.append(line).append("\n"));
                }
                Files.write(configFile.toPath(), contents.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            config = gson.fromJson(new FileReader(configFile), JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        logToConsole.set(config.get("logToConsole").getAsBoolean());

    }

    public void save() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("logToConsole", logToConsole.get());

        try {
            Files.write(configFile.toPath(), gson.toJson(jsonObject).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AtomicBoolean getLogToConsole() {
        return logToConsole;
    }
}
