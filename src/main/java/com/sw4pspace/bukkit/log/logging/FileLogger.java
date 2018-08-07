package com.sw4pspace.bukkit.log.logging;

import com.sw4pspace.bukkit.log.notifier.Notifier;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class FileLogger {

    private Logger logger;

    public FileLogger(File dataFolder, Notifier notifier) {
        logger = Logger.getLogger(UUID.randomUUID().toString());
        try {
            File logDir = new File(dataFolder, "logs" + File.separatorChar + notifier.getName());
            if(!logDir.exists())
                logDir.mkdirs();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy-hh-mm");
            FileHandler fileHandler = new FileHandler(logDir.getAbsolutePath() + File.separatorChar + notifier.getName() + "." + simpleDateFormat.format(new Date()) + ".log");
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            fileHandler.setFormatter(new LogFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
