package me.sirimperivm.spigot.modules.commands;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.managers.ConfigManager;
import me.sirimperivm.spigot.assets.managers.GuiManager;
import me.sirimperivm.spigot.assets.others.General;
import me.sirimperivm.spigot.assets.utils.Colors;
import me.sirimperivm.spigot.assets.utils.Errors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("all")
public class BankCommand implements CommandExecutor {

    private static ConfigManager conf = Main.getConf();

    private static String Prefix(String id) {
        return General.Prefix(id);
    }

    private void getUsage(CommandSender s){
        for (String usage : conf.getSettings().getStringList("messages.help.bank-command")) {
            s.sendMessage(Colors.text(usage));
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] a) {

        if (c.getName().equalsIgnoreCase("bank")) {
            if (Errors.noPerm(s, conf.getSettings().getString("permissions.commands.bank.main"))) {
                return true;
            } else {
                if (a.length == 0) {
                    if (Errors.noConsole(s)) {
                        return true;
                    } else {
                        Player p = (Player) s;
                        GuiManager gm = new GuiManager();
                        p.openInventory(gm.bankGui());
                    }
                } else {
                    getUsage(s);
                }
            }
        }
        return false;
    }
}
