package me.sirimperivm.spigot.assets.utils;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.managers.ConfigManager;
import me.sirimperivm.spigot.assets.others.General;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("all")
public class Errors {

    private static ConfigManager conf = Main.getConf();

    private static String Prefix() {
        return General.Prefix("fail");
    }

    public static boolean noPerm(CommandSender s, String p) {
        if (s.hasPermission(p))
            return false;
        s.sendMessage(Prefix() + ConfigManager.getSetting("messages.errors.no-perm")
                .replace("$node", p));
        return true;
    }

    public static boolean noConsole(CommandSender s) {
        if (s instanceof Player)
            return false;
        s.sendMessage(Prefix() + ConfigManager.getSetting("messages.errors.no-console"));
        return true;
    }
}
