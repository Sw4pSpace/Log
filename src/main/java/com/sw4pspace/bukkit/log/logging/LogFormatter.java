package com.sw4pspace.bukkit.log.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    private static final DateFormat df = new SimpleDateFormat("MMM dd yyyy ~hh:mm:ss.SSS");


    @Override
    public String format(LogRecord record) {
        return df.format(new Date()) + " - " + record.getMessage() + "\n";
    }
}
