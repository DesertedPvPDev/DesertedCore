package codes.matthewp.desertedcore;

import codes.matthewp.desertedcore.config.api.ConfigFile;
import codes.matthewp.desertedcore.event.EventManager;
import codes.matthewp.desertedcore.message.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Core features for deserted
 */
public class DesertedCore extends JavaPlugin {

    private ConfigFile disabledCommands;
    private ConfigFile messages;

    private EventManager eventManager;

    @Override
    public void onEnable() {
        loadFiles();
        eventManager = new EventManager(this);
        eventManager.registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void loadFiles() {
        disabledCommands = new ConfigFile(this, "disabled", "0.0.1", getLogger());
        messages = new ConfigFile(this, "messages", "0.0.1", getLogger());


        new MessageUtil(messages.getConfig());
    }

    public ConfigFile getDisabledCommands() {
        return disabledCommands;
    }


}
