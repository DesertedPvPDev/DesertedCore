package codes.matthewp.desertedcore.string;

import org.bukkit.ChatColor;

public class ColorHelper {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
