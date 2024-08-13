package de.zombyxl.slabcityrp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.ChatColor;
import java.io.File;
import java.io.IOException;
import org.bukkit.Location;

public class ConfigurationBuilder {

    private final String fileName;
    private File file;
    private boolean loadDefault;
    private YamlConfiguration configuration;

    public ConfigurationBuilder(String directory, String fileName) {
        this.fileName = fileName;
        this.file = new File(directory, fileName + ".yml");
        this.loadDefault = !this.file.exists();
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void set(String path, Object object) {
        this.configuration.set(path, object);
    }

    public Object getOrSet(String path, Object defaultValue) {
        if(this.configuration.contains(path)) {
            return get(path);
        } else {
            set(path, defaultValue);
            return defaultValue;
        }
    }


    public void setLocation(String path, Location location) {
        this.configuration.set(path + ".world", location.getWorld().getName());
        this.configuration.set(path + ".x", location.getX());
        this.configuration.set(path + ".y", location.getY());
        this.configuration.set(path + ".z", location.getZ());
        this.configuration.set(path + ".yaw", location.getYaw());
        this.configuration.set(path + ".pitch", location.getPitch());
    }

    public Location getLocation(String path) {
        if (this.configuration.contains(path)) {
            String worldName = this.configuration.getString(path + ".world");
            double x = this.configuration.getDouble(path + ".x");
            double y = this.configuration.getDouble(path + ".y");
            double z = this.configuration.getDouble(path + ".z");
            float yaw = (float) this.configuration.getDouble(path + ".yaw");
            float pitch = (float) this.configuration.getDouble(path + ".pitch");
            return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
        }
        return null;
    }


    public Object get(String path) {
        Object obj = this.configuration.get(path);
        if (obj instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', (String) obj);
        }
        return obj;
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException exception) {
            System.err.println("Could not save configuration file! [file: " + file.getName() + "]");
            exception.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public boolean loadDefault() {
        return this.loadDefault;
    }
}