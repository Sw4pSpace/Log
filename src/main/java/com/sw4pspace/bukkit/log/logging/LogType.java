package com.sw4pspace.bukkit.log.logging;

public enum LogType {

    FILE,
    WEBHOOK;

    public static LogType fromString(String name) {
        for(LogType type : values()) {
            if(type.name().equalsIgnoreCase(name)){
                return type;
            }
        }
        return null;
    }

}
