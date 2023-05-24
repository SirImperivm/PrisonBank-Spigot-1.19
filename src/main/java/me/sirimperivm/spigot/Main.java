package me.sirimperivm.spigot;

import me.sirimperivm.spigot.assets.managers.*;
import me.sirimperivm.spigot.assets.utils.Colors;
import me.sirimperivm.spigot.modules.commands.AdminCommand;
import me.sirimperivm.spigot.modules.commands.BankCommand;
import me.sirimperivm.spigot.modules.listeners.ClickListener;
import me.sirimperivm.spigot.modules.listeners.InteractionListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public final class Main extends JavaPlugin {

    private static Main plugin;
    private static ConfigManager conf;
    private static VaultManager vm;
    private static XPrisonManager xp;
    private static ModuleManager mm;
    private static GuiManager gm;

    void startUp() {
        plugin = this;
        conf = new ConfigManager();
        conf.loadAll();
        mm = new ModuleManager();
        vm = new VaultManager();
        xp = new XPrisonManager();
        gm = new GuiManager();
    }

    void endUp() {
        conf.saveAll();
    }

    @Override
    public void onEnable() {
        startUp();
        getServer().getPluginCommand("bank").setExecutor(new BankCommand());
        getServer().getPluginCommand("adminbank").setExecutor(new AdminCommand());
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new InteractionListener(), this);
        getServer().getConsoleSender().sendMessage(Colors.text("&a[PrisonBank] Plugin attivato correttamente!"));
    }

    @Override
    public void onDisable() {
        endUp();
        getServer().getConsoleSender().sendMessage(Colors.text("&a[PrisonBank] Plugin disattivato correttamente!"));
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static ConfigManager getConf() {
        return conf;
    }

    public static ModuleManager getMm() {
        return mm;
    }

    public static VaultManager getVm() {
        return vm;
    }

    public static XPrisonManager getXp() {
        return xp;
    }

    public static GuiManager getGm() {
        return gm;
    }
}
