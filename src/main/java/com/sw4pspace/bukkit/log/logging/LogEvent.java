package com.sw4pspace.bukkit.log.logging;

public enum LogEvent {

    // Native Bukkit events
    PLAYER_CHAT,
    CONSOLE,
    PLAYER_TELEPORT,
    PLAYER_COMMAND,
    PLAYER_BLOCK_BREAK,
    PLAYER_BLOCK_PLACE,
    PLAYER_ATTACK,
    PLAYER_ATTACK_PLAYER,
    PLAYER_JOIN,
    PLAYER_QUIT,
    PLAYER_KICK,
    PLAYER_ADVANCEMENT_DONE,
    PLAYER_EXP_CHANGE,
    PLAYER_LEVEL_CHANGE,
    PLAYER_GAMEMODE_CHANGE,
    PLAYER_DROP_ITEM,
    PLAYER_WORLD_CHANGE,
    PLAYER_BED_ENTER,
    PLAYER_BED_LEAVE;

    public static LogEvent fromString(String name) {
        for(LogEvent event : values()) {
            if(event.name().equalsIgnoreCase(name)){
                return event;
            }
        }
        return null;
    }

}
