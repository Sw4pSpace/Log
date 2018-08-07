package com.sw4pspace.bukkit.log.logging;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sw4pspace.bukkit.log.util.HTTP;

import java.io.IOException;
import java.util.Map;

public class WebHookLogger {

    public void log(String message, String target) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("text", message);

        Map<String, String> headers = Maps.newHashMap();
        headers.put("Content-Type", "application/json");
        try {
            HTTP.request(target,
                    headers,
                    gson.toJson(jsonObject),
                    "POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
