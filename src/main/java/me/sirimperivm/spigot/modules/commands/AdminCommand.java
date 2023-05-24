package me.sirimperivm.spigot.modules.commands;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.managers.ConfigManager;
import me.sirimperivm.spigot.assets.others.General;
import me.sirimperivm.spigot.assets.utils.Colors;
import me.sirimperivm.spigot.assets.utils.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Set;

@SuppressWarnings("all")
public class AdminCommand implements CommandExecutor {

    private static ConfigManager conf = Main.getConf();

    private static String Prefix(String id) {
        return General.Prefix(id);
    }

    private void getUsage(CommandSender s) {
        for (String usage : conf.getGuis().getStringList("messages.help.admin-command")) {
            s.sendMessage(Colors.text(usage));
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {

        if (c.getName().equalsIgnoreCase("adminbank")) {
            if (Errors.noPerm(s, conf.getSettings().getString("permissions.commands.adminbank.main"))) {
                return true;
            } else {
                if (a.length == 0) {
                    getUsage(s);
                }
                else if (a.length == 1) {
                    if (a[0].equalsIgnoreCase("reload")) {
                        conf.loadAll();
                        s.sendMessage(Prefix("success") + ConfigManager.getSetting("messages.success.plugin.reloaded"));
                    }
                    else {
                        getUsage(s);
                    }
                }
                else {
                    getUsage(s);
                }
            }
        }
        return false;
    }
}
