package com.sw4pspace.bukkit.log.logging;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sw4pspace.bukkit.log.Config;
import com.sw4pspace.bukkit.log.Log;
import com.sw4pspace.bukkit.log.notifier.MessageHandler;
import com.sw4pspace.bukkit.log.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Sw4pLogger {

    private static final Sw4pLogger instance = new Sw4pLogger();

    private static MessageHandler messageHandler;

    private static final Map<LogEvent, List<Notifier>> registry = Maps.newHashMap();

    public static Sw4pLogger getLogger() {
        return instance;
    }

    public static void init(JavaPlugin plugin) {
        messageHandler = new MessageHandler(plugin.getDataFolder());
    }

    public static void registerNotifier(final Notifier notifier) {
        notifier.getEvents().forEach(logEvent -> {
            if(registry.containsKey(logEvent)) {
                registry.get(logEvent).add(notifier);
            }else{
                registry.put(logEvent, Lists.newArrayList(notifier));
            }
        });
    }

    public void log(LogEvent eventType, Event event) {
        Log.runTask(() -> {
            List<Notifier> notifiers = registry.get(eventType);

            if(notifiers == null || notifiers.isEmpty())
                return;

            String message =  messageHandler.getMessage(eventType, event);
            if(Config.getLogToConsole().get())
                Log.getBukkitLogger().log(Level.INFO, message);

            notifiers.forEach(notifier -> {
                switch (notifier.getType()) {
                    case FILE:
                        notifier.getFileLogger().getLogger().log(Level.INFO, message);
                        break;
                    case WEBHOOK:
                        notifier.getWebHookLogger().log(message, notifier.getWebhook());
                        break;
                }
            });
        });
    }
}
