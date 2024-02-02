package gg.quartzdev.qtradetags.util;

public enum Messages {
    //    Prefix
    CONSOLE_PREFIX("<gray>[<red>q<aqua>TradeTags<gray>]"),
    CHAT_PREFIX("<red>q<aqua>Trade-Tags <bold><gray>></bold>"),

    //    Generic Plugin
    PLUGIN_INFO("<prefix> <green>Running version <gray><version>"),
    RELOAD_COMPLETE("<prefix> <green>Config reloaded"),
    ERROR_CMD_NOT_FOUND("<prefix> <red>Error: Command not found: <yellow><cmd>"),
    ERROR_NO_PERMISSION("<prefix> <red>Error: You don't have permission to perform this"),
    ERROR_PLAYER_ONLY_COMMAND("<prefix> <red>Error: You must be a player to run this command"),
    ERROR_CONSOLE_ONLY_COMMAND("<prefix> <red>Error: This command can only be ran from the console"),

    //    Generic File
    FILE_CREATED("<prefix> <green>Created file: <yellow><file>"),
    ERROR_CREATE_FILE("<prefix> <red>Error creating file: <yellow><file>"),
    ERROR_SAVE_FILE("<prefix> <red>Error saving file: <yellow><file>"),
    ERROR_CORRUPT_FILE("<prefix> <red>Error: Corrupt file: <yellow><file></yellow><newline>Please reset the file."),

    //    qTradeTags Messages


    END("");


    private final String message;
    private String parsedMessage;

    Messages(String msg){
        this.message = msg;
        this.parsedMessage = msg;
    }

    @Override
    public String toString(){
        return this.message;
    }

    public String get(){
        String result = this.parsedMessage;
        this.parsedMessage = this.message;
        return result;
    }

    public Messages parse(String placeholder, String value){
        this.parsedMessage = this.parsedMessage.replaceAll("<" + placeholder + ">", value);
        return this;
    }
}
