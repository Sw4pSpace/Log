package com.sw4pspace.bukkit.log.util;

import org.bukkit.Location;

public class Util {

    public static String getLocationString(Location loc) {
        if (loc == null)
            return "";
        return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch() ;
    }

    public static String getBlockString(Location loc) {
        if (loc == null)
            return "";
        return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ() ;
    }


}
