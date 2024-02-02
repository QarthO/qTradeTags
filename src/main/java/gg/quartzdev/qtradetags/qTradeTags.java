package gg.quartzdev.qtradetags;

import gg.quartzdev.qtradetags.listeners.TradeListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qTradeTags extends JavaPlugin {

    public static qTradeTags instance;

    @Override
    public void onEnable() {
        instance = this;
        registerMetrics();
        registerEventHandlers();
    }

    @Override
    public void onDisable() {

    }

    public void registerMetrics(){
        int pluginId = 20879; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);
    }

    public void registerEventHandlers(){
        Bukkit.getPluginManager().registerEvents(new TradeListener(), this);
    }
}
