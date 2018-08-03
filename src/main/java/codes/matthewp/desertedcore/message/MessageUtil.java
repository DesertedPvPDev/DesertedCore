package codes.matthewp.desertedcore.message;

import codes.matthewp.desertedcore.string.ColorHelper;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class MessageUtil {

    private static HashMap<String, String> messages;

    public MessageUtil(FileConfiguration msg) {
        messages = new HashMap<>();
        for (String s : msg.getConfigurationSection("messages").getKeys(false)) {
            messages.put(s, ColorHelper.color(msg.getString("messages." + s)));
        }
    }


    public static String getMessage(String key) {
        if(messages.containsKey(key)) {
            return messages.get(key);
        } else {
            return "Uncaught null string: " + key;
        }
    }

}
