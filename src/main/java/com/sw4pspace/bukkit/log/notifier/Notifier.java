package com.sw4pspace.bukkit.log.notifier;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sw4pspace.bukkit.log.logging.FileLogger;
import com.sw4pspace.bukkit.log.logging.LogEvent;
import com.sw4pspace.bukkit.log.logging.LogType;
import com.sw4pspace.bukkit.log.logging.WebHookLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.JSONParser;

import java.util.List;

public class Notifier {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String name;
    private LogType type;
    private List<LogEvent> events = Lists.newArrayList();

    // For webhooks
    private String webhook;

    private JSONParser parser = new JSONParser();
    private FileLogger fileLogger;
    private WebHookLogger webHookLogger = new WebHookLogger();

    public Notifier(String name, LogType type, List<LogEvent> events) {
        this.name = name;
        this.type = type;
        this.events = events;
        this.webhook = "";
    }

    public Notifier(String name, LogType type, List<LogEvent> events, String webhook) {
        this.name = name;
        this.type = type;
        this.events = events;
        this.webhook = webhook;
    }

    public Notifier(String json, JavaPlugin plugin) {

        try {
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            this.name = jsonObject.get("name").getAsString();
            this.type = LogType.fromString(jsonObject.get("type").getAsString());
            JsonArray eventsArray = jsonObject.getAsJsonArray("events");
            eventsArray.forEach(ev -> events.add(LogEvent.fromString(ev.getAsString())));

            if(this.type.equals(LogType.WEBHOOK)) {
                this.webhook = jsonObject.get("webhook").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileLogger = new FileLogger(plugin.getDataFolder(), this);
    }

    public String getName() {
        return name;
    }

    public LogType getType() {
        return type;
    }

    public List<LogEvent> getEvents() {
        return events;
    }

    public String getWebhook() {
        return webhook;
    }

    public FileLogger getFileLogger() {
        return fileLogger;
    }

    public WebHookLogger getWebHookLogger() {
        return webHookLogger;
    }

    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("type", type.toString());

        JsonArray eventsArr = new JsonArray();
        this.events.forEach(event -> eventsArr.add(event.toString()));
        jsonObject.add("events", eventsArr);

        if(this.type.equals(LogType.WEBHOOK)) {
            jsonObject.addProperty("webhook", webhook);
        }

        return gson.toJson(jsonObject);
    }
}
