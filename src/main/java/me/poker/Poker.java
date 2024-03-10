package me.poker;

import org.bukkit.plugin.java.JavaPlugin;

public final class Poker extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin Starting!");
        getCommand("draw").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
