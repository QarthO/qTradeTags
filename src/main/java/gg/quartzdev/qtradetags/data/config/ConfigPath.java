package gg.quartzdev.qtradetags.data.config;

public enum ConfigPath {

    REQUIRES_PERMISSION("requires-permission"),
    IGNORE_CUSTOM_MERCHANTS("ignore-custom-merchants"),

    NULL("");

    private final String path;

    ConfigPath(String path){
        this.path = path;
    }

    @Override
    public String toString(){
        return this.get();
    }

    public String get(){
        return this.path;
    }

}
