package com.sw4pspace.bukkit.log.notifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sw4pspace.bukkit.log.logging.LogEvent;
import com.sw4pspace.bukkit.log.util.Util;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.*;
import java.nio.file.Files;

public class MessageHandler {

    private JsonObject messages;

    public MessageHandler(File dataFolder) {
        File messageFile = new File(dataFolder, "messages.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if(!messageFile.exists()) {
            try {
                StringBuilder contents = new StringBuilder();
                try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("messages.json")) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    reader.lines().forEach(line -> contents.append(line).append("\n"));
                }
                Files.write(messageFile.toPath(), contents.toString().getBytes());
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
        try {
            messages = gson.fromJson(new FileReader(messageFile), JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getMessage(LogEvent logEvent, Event event) {

        if(messages.has(logEvent.name())) {
            String template = messages.get(logEvent.name()).getAsString();

            if(event instanceof PlayerEvent){
                template = template.replaceAll("<player>", ((PlayerEvent) event).getPlayer().getDisplayName());
                template = template.replaceAll("<uuid>", ((PlayerEvent) event).getPlayer().getUniqueId().toString());
            }

            if(event instanceof ServerCommandEvent) {
                template = template.replaceAll("<command>", ((ServerCommandEvent) event).getCommand());
            }

            if(event instanceof AsyncPlayerChatEvent) {
                template = template.replaceAll("<message>", ((AsyncPlayerChatEvent) event).getMessage());
            }

            if(event instanceof BlockEvent) {
                template = template.replaceAll("<location>", Util.getBlockString(((BlockEvent) event).getBlock().getLocation()));
            }

            if(event instanceof PlayerTeleportEvent) {
                template = template.replaceAll("<to>", Util.getLocationString(((PlayerTeleportEvent) event).getTo()));
                template = template.replaceAll("<from>", Util.getLocationString(((PlayerTeleportEvent) event).getFrom()));
            }

            if(event instanceof EntityDamageByEntityEvent) {
                template = template.replaceAll("<targetPlayer>", ((EntityDamageByEntityEvent) event).getEntity().getName());
                template = template.replaceAll("<targetUuid>", ((EntityDamageByEntityEvent) event).getEntity().getUniqueId().toString());
                template = template.replaceAll("<player>", ((EntityDamageByEntityEvent) event).getDamager().getName());
                template = template.replaceAll("<uuid>", ((EntityDamageByEntityEvent) event).getDamager().getUniqueId().toString());
                template = template.replaceAll("<location>", Util.getLocationString(((EntityDamageByEntityEvent) event).getEntity().getLocation()));
            }

            if(event instanceof EntityEvent) {
                template = template.replaceAll("<player>", ((EntityEvent) event).getEntity().getName());
                template = template.replaceAll("<uuid>", ((EntityEvent) event).getEntity().getUniqueId().toString());
            }

            if(event instanceof PlayerCommandPreprocessEvent) {
                template = template.replaceAll("<command>", ((PlayerCommandPreprocessEvent) event).getMessage());
            }

            if(event instanceof PlayerKickEvent) {
                template = template.replaceAll("<reason>", ((PlayerKickEvent) event).getReason());
            }

            if(event instanceof PlayerAdvancementDoneEvent) {
                template = template.replaceAll("<advancement>", ((PlayerAdvancementDoneEvent) event).getAdvancement().getKey().getKey());
            }

            if(event instanceof PlayerExpChangeEvent){
                template = template.replaceAll("<exp>", String.valueOf(((PlayerExpChangeEvent) event).getPlayer().getExp()));
                template = template.replaceAll("<expAmount>", String.valueOf(((PlayerExpChangeEvent) event).getAmount()));
            }

            if(event instanceof PlayerLevelChangeEvent) {
                template = template.replaceAll("<oldLevel>", String.valueOf(((PlayerLevelChangeEvent) event).getOldLevel()));
                template = template.replaceAll("<newLevel>", String.valueOf(((PlayerLevelChangeEvent) event).getNewLevel()));
            }

            if(event instanceof PlayerGameModeChangeEvent) {
                template = template.replaceAll("<gamemode>", ((PlayerGameModeChangeEvent) event).getNewGameMode().name());
            }

            if(event instanceof PlayerDropItemEvent) {
                template = template.replaceAll("<item>", ((PlayerDropItemEvent) event).getItemDrop().getItemStack().getType().name());
                template = template.replaceAll("<itemLocation>", Util.getLocationString(((PlayerDropItemEvent) event).getPlayer().getLocation()));
            }

            if(event instanceof PlayerChangedWorldEvent) {
                template = template.replaceAll("<newWorld>", ((PlayerChangedWorldEvent) event).getPlayer().getWorld().getName());
                template = template.replaceAll("<oldWorld>", ((PlayerChangedWorldEvent) event).getFrom().getName());
            }

            if(event instanceof PlayerBedEnterEvent) {
                template = template.replaceAll("<bedLocation>", Util.getBlockString(((PlayerBedEnterEvent) event).getBed().getLocation()));
            }

            if(event instanceof PlayerBedLeaveEvent) {
                template = template.replaceAll("<bedLocation>", Util.getBlockString(((PlayerBedLeaveEvent) event).getBed().getLocation()));
            }

            if(event instanceof BlockBreakEvent) {
                BlockBreakEvent bEvent = (BlockBreakEvent) event;
                String name = bEvent.getPlayer() != null ? bEvent.getPlayer().getDisplayName() : "Unknown player";
                String uuid = bEvent.getPlayer() != null ? bEvent.getPlayer().getUniqueId().toString() : "Unknown UUID";
                template = template.replaceAll("<player>",  name);
                template = template.replaceAll("<uuid>", uuid);
            }

            if(event instanceof BlockPlaceEvent) {
                BlockPlaceEvent pEvent = (BlockPlaceEvent) event;
                String name = pEvent.getPlayer() != null ? pEvent.getPlayer().getDisplayName() : "Unknown player";
                String uuid = pEvent.getPlayer() != null ? pEvent.getPlayer().getUniqueId().toString() : "Unknown UUID";
                template = template.replaceAll("<player>",  name);
                template = template.replaceAll("<uuid>", uuid);
            }

            return template;
        }

        return "Error, Unknown event.";
    }

}
