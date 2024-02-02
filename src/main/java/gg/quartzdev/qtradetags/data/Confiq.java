package gg.quartzdev.qtradetags.data;

import gg.quartzdev.qtradetags.qTradeTags;
import gg.quartzdev.qtradetags.util.Loqqer;
import org.bukkit.configuration.file.FileConfiguration;

public class Confiq {

    qTradeTags plugin;
    Loqqer logger;
    FileConfiguration file;

    public Confiq(){
        plugin = qTradeTags.instance;

        file = plugin.getConfig();
        plugin.saveDefaultConfig();

        loadAll();
    }

    private void save(){
        plugin.saveConfig();
    }

    public void reload(){

        plugin.reloadConfig();
        file = plugin.getConfig();

        save();
        loadAll();;
    }

    public void loadAll(){

    }

}
