package codes.matthewp.desertedcore.manager;

import codes.matthewp.desertedcore.string.ColorHelper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages broadcasting
 */
public class BroadcastManager {

    /**
     * The format to use
     */
    private String format;

    /**
     * List of messages
     */
    private List<String> messages;

    /**
     * Random or ordered messages
     */
    private boolean random;

    /**
     * The timer in ticks
     */
    private int timer;

    /**
     * The current message
     */
    private int currentMessage;

    /**
     * Regex pattern for the {message}
     */
    private Pattern messagePattern = Pattern.compile("\\{(?i)message}");

    /**
     * Load the broadcaster manager
     * @param section config
     * @param plugin javaplugin
     */
    public BroadcastManager(ConfigurationSection section, JavaPlugin plugin) {
        load(section);
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(random) {
                    Random ran = new Random();
                    int messageToUse = ran.nextInt(messages.size());
                    Matcher matcher = messagePattern.matcher(format);
                    String msg = matcher.replaceAll(messages.get(messageToUse));
                    Bukkit.broadcastMessage(ColorHelper.color(msg));
                }
            }
        }, 0L, timer);
    }

    /**
     * Load the config values
     * @param sec config
     */
    private void load(ConfigurationSection sec) {
        format = sec.getString("format");
        messages = sec.getStringList("messages");
        random = sec.getBoolean("random");
        resolveTimer(sec.getString("time"));
        currentMessage = 0;
        messages = ColorHelper.colorList(messages);
    }

    /**
     * Resolve the timer string
     *
     * This takes a time, such as 5m,
     * and converts it into game ticks
     * @param timer string to resolve from
     */
    private void resolveTimer(String timer) {
        String timeUnit = timer.substring(timer.length() - 1);
        int rawTime = Integer.valueOf(timer.substring(0, timer.length() - 1));
        if (timeUnit.equalsIgnoreCase("s")) {
            // Ticks = 20 * seconds
            this.timer = rawTime * 20;
        } else if (timeUnit.equalsIgnoreCase("m")) {
            // minutes * 60 * 20
            this.timer = rawTime * 60 * 20;
        } else if (timeUnit.equalsIgnoreCase("h")) {
            // hours * minues * seconds * 20
            this.timer = rawTime * 60 * 60 * 20;
        }
    }
}
