package codes.matthewp.desertedcore.event;

import codes.matthewp.desertedcore.DesertedCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private PluginManager manager;

    private DesertedCore plugin;

    public EventManager(DesertedCore plugin) {
        manager = Bukkit.getServer().getPluginManager();
        this.plugin = plugin;
    }

    public void registerEvents() {
        manager.registerEvents(new EventPreCommand(plugin.getDisabledCommands().getStringList("disabledCommands")), plugin);
    }
}
