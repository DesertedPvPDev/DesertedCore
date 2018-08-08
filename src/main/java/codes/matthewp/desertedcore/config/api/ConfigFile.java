package codes.matthewp.desertedcore.config.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * A simple utility class to make configurations files
 * Simply create a new instance and use needed methods
 * NOTE: This class automagicly add default values to prevent NPE's
 */
public class ConfigFile {

    /**
     * The plugin instance
     */
    private JavaPlugin plugin;

    /**
     * The file representation for this configuration
     */
    private File file;

    /**
     * The configuration object
     */
    private FileConfiguration config;

    /**
     * Local file name
     */
    private String fileName;

    /**
     * The plugins file resource name
     */
    private String resourceName;

    /**
     * Plugins logger
     */
    private Logger logger;

    /**
     * Create a new config file
     * @param pl JavaPlugin instance
     * @param configName String name of config without .yml
     * @param version String version to load the config at
     * @param logger Logger logger to write logs to
     */
    public ConfigFile(JavaPlugin pl, String configName, String version, Logger logger) {
        this.plugin = pl;
        fileName = pl.getDataFolder() + File.separator + configName + ".yml";
        this.resourceName = configName + ".yml";
        this.file = new File(fileName);
        this.logger = logger;
        reload();
    }

    /**
     * Get a string value
     * @param key String key for the string
     * @return String value fetched from key
     */
    public String getString(String key) {
        if (config.getString(key) != null && !config.getString(key).equals("")) {
            return config.getString(key);
        } else {
            return "Missing configuration value: " + key + " in file: " + file.getName();
        }
    }

    /**
     * Get a boolean value
     * @param key String key to fetch boolean
     * @return boolean value based on key
     */
    public boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    /**
     * Get a list of strings
     * @param key String key to fetch this list
     * @return List<String> values
     */
    public List<String> getStringList(String key) {
        if (config.getStringList(key) != null && !config.getStringList(key).isEmpty()) {
            return config.getStringList(key);
        } else {
            List<String> nullVal = new ArrayList<>();
            nullVal.add("Missing string list configuration value: " + key + " in file: " + file.getName());
            return nullVal;
        }
    }

    /**
     * Reload the configuration file
     */
    public void reload() {
        if (config == null) {
            setupFiles();
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Makes sure needed files exist
     */
    private void setupFiles() {
        if (!plugin.getDataFolder().exists()) {
            if (plugin.getDataFolder().mkdir()) {
                logger.info("Created missing plugin datafolder.");
            } else {
                logger.info("Failed to create missing plugin data folder. Errors may ensue.");
            }
        }

        if (!file.exists()) {
            plugin.saveResource(resourceName, true);
            logger.info("Created missing config file: " + file.getName());
        }
    }

    /**
     * Get the config object
     * @return The file configuration object
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Get the file object
     * @return File file object
     */
    public File getFile() {
        return file;
    }
}
