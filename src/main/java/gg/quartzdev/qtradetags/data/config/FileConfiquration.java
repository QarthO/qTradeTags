package gg.quartzdev.qtradetags.data.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class FileConfiquration extends FileConfiguration {
    @Override
    public @NotNull String saveToString() {
        return null;
    }

    @Override
    public void loadFromString(@NotNull String s) throws InvalidConfigurationException {

    }

    public Number getNumber(){


        return 0;
    }
}
