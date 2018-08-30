package codes.matthewp.desertedcore;

import codes.matthewp.desertedcore.command.DiscordCmd;
import codes.matthewp.desertedcore.command.MessageCmd;
import codes.matthewp.desertedcore.config.api.ConfigFile;
import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.event.EventManager;
import codes.matthewp.desertedcore.message.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Core features for deserted
 */
public class DesertedCore extends JavaPlugin {

    private ConfigFile disabledCommands;
    private ConfigFile messages;
    private ConfigFile database;
    private ConfigFile config;

    private EventManager eventManager;
    private Database db;

    private static DesertedCore core;

    @Override
    public void onEnable() {
        core= this;
        loadFiles();
        db = new Database(database.getConfig());
        eventManager = new EventManager(this);
        eventManager.registerEvents();
        loadCommands();
    }

    @Override
    public void onDisable() {

    }

    private void loadFiles() {
        disabledCommands = new ConfigFile(this, "disabled", "0.0.1", getLogger());
        messages = new ConfigFile(this, "messages", "0.0.2", getLogger());
        database = new ConfigFile(this, "database", "0.0.1", getLogger());
        config = new ConfigFile(this, "config", "0.0.1", getLogger());

        new MessageUtil(messages.getConfig());
    }

    private void loadCommands() {
        getCommand("msg").setExecutor(new MessageCmd(this));
        getCommand("discord").setExecutor(new DiscordCmd(this));
    }

    public ConfigFile getDisabledCommands() {
        return disabledCommands;
    }

    public Database getDB() {
        return db;
    }

    public ConfigFile getConfiguration() {
        return config;
    }

    public static DesertedCore getCore() {
        return core;
    }
}
