package gg.quartzdev.qtradetags.data.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ConfigOption<T> {

    private ConfigPath path;
    private T value;

    public ConfigOption(ConfigPath path, T defaultValue){
        this.path = path;
        this.value = defaultValue;
    }

    public T value(){
        return value;
    }

    public String path(){
        return path.get();
    }

    private @Nullable Object loadRawData(FileConfiguration file){
        return file.get(path());
    }

    public void set(T newValue){
        this.value = newValue;
    }

}
