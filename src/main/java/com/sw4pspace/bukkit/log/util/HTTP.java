package com.sw4pspace.bukkit.log.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HTTP {

    public static String request(String target, Map<String, String> headers, String body, String method) throws IOException {

        URL targetUrl = new URL(target);
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();

        if(headers != null && !headers.isEmpty()){
            headers.forEach(connection::setRequestProperty);
        }

        connection.setRequestMethod(method.toUpperCase());
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);

        if(body != null && !body.isEmpty()) {
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(body);
            outputStream.flush();
            outputStream.close();
        }

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = inputStream.readLine()) != null) {
            response.append(inputLine);
        }
        inputStream.close();

        connection.disconnect();
        return response.toString();
    }
}
