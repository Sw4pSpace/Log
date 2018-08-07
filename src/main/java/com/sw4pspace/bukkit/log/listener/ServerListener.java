package com.sw4pspace.bukkit.log.listener;

import com.sw4pspace.bukkit.log.logging.LogEvent;
import com.sw4pspace.bukkit.log.logging.Sw4pLogger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ServerListener implements Listener {

    private static final Sw4pLogger logger = Sw4pLogger.getLogger();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onConsoleInput(ServerCommandEvent event) {
        logger.log(LogEvent.CONSOLE, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRemoteConsoleInput(RemoteServerCommandEvent event) {
        logger.log(LogEvent.CONSOLE, event);
    }

}
