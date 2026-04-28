package com.pluginforge.advancedrtp;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedRtp extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AdvancedRtp v1.0 enabled.");
        getCommand("rtp").setExecutor(new RtpCommand());
        getCommand("rtpadmin").setExecutor(new RtpadminCommand());
        getCommand("rtpadmins").setExecutor(new RtpadminsCommand());

    }

    @Override
    public void onDisable() {
        getLogger().info("AdvancedRtp disabled.");
    }
}
