package codes.matthewp.desertedcore.event;

import codes.matthewp.desertedcore.message.MessageUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class EventPreCommand implements Listener {

    private List<String> blockedCommands;

    public EventPreCommand(List<String> disabled) {
        this.blockedCommands = disabled;
    }

    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent event) {
        if(!event.getPlayer().hasPermission("deserted.overridedisable")) {
            for(String s : blockedCommands) {
                if(event.getMessage().equalsIgnoreCase(s)) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(MessageUtil.getMessage("commandDisabled"));
                }
            }
        }
    }
}
