package me.sirimperivm.spigot.assets.managers;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.utils.Colors;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class ConfigManager {

    private static Main plugin = Main.getPlugin();
    private static Logger log = Logger.getLogger("PrisonBank");
    private static File folder = plugin.getDataFolder();
    private static File settingsFile, guisFile;
    private static FileConfiguration settings, guis;

    public ConfigManager() {
        settingsFile = new File(folder, "settings.yml");
        settings = new YamlConfiguration();
        guisFile = new File(folder, "guis.yml");
        guis = new YamlConfiguration();

        if (!folder.exists()) {
            folder.mkdir();
        }

        if (!settingsFile.exists()) {
            create(settings, settingsFile);
        }

        if (!guisFile.exists()) {
            create(guis, guisFile);
        }
    }

    private void create(FileConfiguration c, File f) {
        String n = f.getName();
        try {
            Files.copy(plugin.getResource(n), f.toPath(), new CopyOption[0]);
            load(c, f);
        } catch (IOException e) {
            log.severe("Impossibile creare il file " + n + "!");
            e.printStackTrace();
        }
    }

    public void save(FileConfiguration c, File f) {
        String n = f.getName();
        try {
            c.save(f);
        } catch (IOException e) {
            log.severe("Impossibile salvare il file " + n + "!");
            e.printStackTrace();
        }
    }

    public void load(FileConfiguration c, File f) {
        String n = f.getName();
        try {
            c.load(f);
        } catch (IOException | InvalidConfigurationException e) {
            log.severe("Impossibile caricare il file " + n + "!");
            e.printStackTrace();
        }
    }

    public void saveAll() {
        save(settings, settingsFile);
    }

    public void loadAll() {
        load(settings, settingsFile);
    }

    public static File getSettingsFile() {
        return settingsFile;
    }

    public static File getGuisFile() {
        return guisFile;
    }

    public static FileConfiguration getSettings() {
        return settings;
    }

    public static FileConfiguration getGuis() {
        return guis;
    }

    public static String getSetting(String s) {
        return Colors.text(Main.getConf().getSettings().getString(s));
    }

    public static String getGui(String s) {
        return Colors.text(Main.getConf().getGuis().getString(s));
    }
}
