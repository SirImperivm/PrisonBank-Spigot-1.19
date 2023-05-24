package me.sirimperivm.spigot.assets.managers;

import me.sirimperivm.spigot.Main;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

@SuppressWarnings("all")
public class VaultManager {

    private static Main plugin = Main.getPlugin();
    private static Economy econ = null;

    public VaultManager() {
        if (!setupEconomy()) {
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEcon() {
        return econ;
    }
}
