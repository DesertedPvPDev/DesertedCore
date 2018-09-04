package codes.matthewp.desertedcore.string;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Help color some stuff!
 */
public class ColorHelper {

    /**
     * Color a string
     * @param s String to color
     * @return Colored rep of the string
     */
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Color a list of strings
     * @param list List to color
     * @return The list, but colored
     */
    public static List<String> colorList(List<String> list) {
        return list.stream().map(s -> color(s)).collect(Collectors.toList());
    }

    /**
     * Strip the color
     * Note: This colors then strips to prevent mishaps
     * @param string String to strip
     * @return String but not colored
     */
    public static String stripColor(String string) {
        return ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', string));
    }
}
