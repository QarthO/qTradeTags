package gg.quartzdev.qtradetags.data.config;

import gg.quartzdev.qtradetags.qTradeTags;
import gg.quartzdev.qtradetags.util.Loqqer;
import org.bukkit.configuration.file.FileConfiguration;

public class Confiq {

    qTradeTags plugin;
    Loqqer logger;
    FileConfiguration file;

    ConfigOption<Boolean> requiresPermission;

    public Confiq(){
        plugin = qTradeTags.instance;

        file = plugin.getConfig();
        plugin.saveDefaultConfig();


        loadAll();
    }

    public void setupConfigOptions(){
        requiresPermission = new ConfigOption<>(ConfigPath.REQUIRES_PERMISSION, true);
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

    public <T> void loadConfigOption(ConfigOption<T> configOption){
        Object rawData = file.get(configOption.path());

        if(rawData == null){
            return;
        }

        configOption.set((T) rawData);
    }

}
