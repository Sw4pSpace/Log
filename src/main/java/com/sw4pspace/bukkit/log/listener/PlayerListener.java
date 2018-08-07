package com.sw4pspace.bukkit.log.listener;

import com.sw4pspace.bukkit.log.logging.LogEvent;
import com.sw4pspace.bukkit.log.logging.Sw4pLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    private static final Sw4pLogger logger = Sw4pLogger.getLogger();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        logger.log(LogEvent.PLAYER_CHAT, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        logger.log(LogEvent.PLAYER_COMMAND, event);
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        logger.log(LogEvent.PLAYER_TELEPORT, event);
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onPlayerBlockBreak(BlockBreakEvent event) {
        logger.log(LogEvent.PLAYER_BLOCK_BREAK, event);
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onPlayerBlockPlace(BlockPlaceEvent event) {
        logger.log(LogEvent.PLAYER_BLOCK_PLACE, event);
    }

    @EventHandler(priority =  EventPriority.MONITOR)
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            logger.log(LogEvent.PLAYER_ATTACK, event);
            if(event.getEntity() instanceof Player) {
                logger.log(LogEvent.PLAYER_ATTACK_PLAYER, event);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        logger.log(LogEvent.PLAYER_JOIN, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        logger.log(LogEvent.PLAYER_QUIT, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKick(PlayerKickEvent event) {
        logger.log(LogEvent.PLAYER_KICK, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        logger.log(LogEvent.PLAYER_ADVANCEMENT_DONE, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        logger.log(LogEvent.PLAYER_EXP_CHANGE, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        logger.log(LogEvent.PLAYER_LEVEL_CHANGE, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent event) {
        logger.log(LogEvent.PLAYER_GAMEMODE_CHANGE, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        logger.log(LogEvent.PLAYER_DROP_ITEM, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        logger.log(LogEvent.PLAYER_WORLD_CHANGE, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        logger.log(LogEvent.PLAYER_BED_ENTER, event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        logger.log(LogEvent.PLAYER_BED_LEAVE, event);
    }

}
