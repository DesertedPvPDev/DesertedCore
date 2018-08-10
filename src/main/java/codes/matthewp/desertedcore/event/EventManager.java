package codes.matthewp.desertedcore.event;

import codes.matthewp.desertedcore.DesertedCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    private DesertedCore plugin;

    public EventManager(DesertedCore plugin) {
        this.plugin = plugin;
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventPreCommand(plugin.getDisabledCommands().getStringList("disabledCommands")), plugin);
        pm.registerEvents(new PingEvent(plugin), plugin);
    }
}
