package codes.matthewp.desertedcore.event;

import codes.matthewp.desertedcore.DesertedCore;
import codes.matthewp.desertedcore.string.ColorHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingEvent implements Listener {

    private DesertedCore core;

    public PingEvent(DesertedCore core) {
        this.core = core;
    }
    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(ColorHelper.color(core.getConfiguration().getString("serverPing")));
        event.setMaxPlayers(event.getNumPlayers() + 1);
    }
}
