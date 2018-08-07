package com.sw4pspace.bukkit.log;

import com.google.common.collect.Lists;
import com.sw4pspace.bukkit.log.listener.PlayerListener;
import com.sw4pspace.bukkit.log.listener.ServerListener;
import com.sw4pspace.bukkit.log.logging.Sw4pLogger;
import com.sw4pspace.bukkit.log.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

public class Log extends JavaPlugin {

    private static Log instance;

    @Override
    public void onEnable() {
        instance = this;
        Config config = new Config();
        config.load(this.getDataFolder());
        Sw4pLogger.init(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        loadNotifiers();
    }

    private void loadNotifiers() {
        File notifierDir = new File(this.getDataFolder(), "notifiers");

        if(!notifierDir.exists())
            notifierDir.mkdirs();

        if(notifierDir.isDirectory() && notifierDir.listFiles() != null && notifierDir.listFiles().length != 0) {
            List<File> files = Lists.newArrayList(notifierDir.listFiles());
            files.stream().map(notifier -> {
                try {
                    return new Notifier(new String(Files.readAllBytes(notifier.toPath())), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).forEach(notifier -> {
                if(notifier != null)
                    Sw4pLogger.registerNotifier(notifier);
            });
        }

    }

    public static Logger getBukkitLogger() {
        return instance.getLogger();
    }

    public static void runTask(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, runnable);
    }

}
