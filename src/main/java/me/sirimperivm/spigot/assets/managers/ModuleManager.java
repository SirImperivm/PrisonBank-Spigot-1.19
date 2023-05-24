package me.sirimperivm.spigot.assets.managers;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.others.General;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ModuleManager {

    private static ConfigManager conf = Main.getConf();
    private static String Prefix(String id){
        return General.Prefix(id);
    }

    public void giveMoneyItem(Player p, double amount) {
        boolean glowing = conf.getSettings().getBoolean("settings.moneyItems.glowing");

        ItemStack is = new ItemStack(Material.getMaterial(conf.getSettings().getString("settings.moneyItems.material")));
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ConfigManager.getSetting("settings.moneyItems.display-name"));
        List<String> lore = new ArrayList<>();
        for (String s : conf.getSettings().getStringList("settings.moneyItems.display-lore")) {
            lore.add(s.replace("$amount", String.valueOf(amount)));
        }
        im.setLore(General.lore(lore));
        if (glowing) {
            im.addEnchant(Enchantment.DURABILITY, 0, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        im.setCustomModelData(15);
        is.setItemMeta(im);

        p.getInventory().addItem(is);
    }

    public static boolean isBalanceRemovable(Player p, String clickType, String balanceName) {
        double money = Main.getVm().getEcon().getBalance(p);
        long tokens = Main.getXp().getTokens(p);
        long gems = Main.getXp().getGems(p);

        boolean value = false;

        switch (clickType) {
            case "LEFT":
                if (balanceName.equalsIgnoreCase("money")) {
                    double price = 100;
                    if (money >= price) {
                        Main.getVm().getEcon().withdrawPlayer(p, price);
                        value = true;
                    } else {
                        p.sendMessage(Prefix("fail") + ConfigManager.getSetting("messages.errors.balance-not-enough")
                                .replace("$balance", balanceName));
                    }
                } else if (balanceName.equalsIgnoreCase("tokens")) {
                    long price = 50;
                    if (tokens >= price) {
                        XPrisonManager.takeTokens(p, price);
                        value = true;
                    } else {
                        p.sendMessage(Prefix("fail") + ConfigManager.getSetting("messages.errors.balance-not-enough")
                                .replace("$balance", balanceName));
                    }
                } else if (balanceName.equalsIgnoreCase("gems")) {
                    long price = 50;
                    if (gems >= price) {
                        XPrisonManager.takeGems(p, price);
                        value = true;
                    } else {
                        p.sendMessage(Prefix("fail") + ConfigManager.getSetting("messages.errors.balance-not-enough")
                                .replace("$balance", balanceName));
                    }
                }
                break;
            case "RIGHT":
                if (balanceName.equalsIgnoreCase("money")) {
                    double price = 1000;
                    if (money >= price) {
                        Main.getVm().getEcon().withdrawPlayer(p, price);
                        value = true;
                    } else {
                        p.sendMessage(Prefix("fail") + ConfigManager.getSetting("messages.errors.balance-not-enough")
                                .replace("$balance", balanceName));
                    }
                } else if (balanceName.equalsIgnoreCase("tokens")) {
                    long price = 500;
                    if (tokens >= price) {
                        XPrisonManager.takeTokens(p, price);
                        value = true;
                    } else {
                        p.sendMessage(Prefix("fail") + ConfigManager.getSetting("messages.errors.balance-not-enough")
                                .replace("$balance", balanceName));
                    }
                } else if (balanceName.equalsIgnoreCase("gems")) {
                    long price = 500;
                    if (gems >= price) {
                        XPrisonManager.takeGems(p, price);
                        value = true;
                    } else {
                        p.sendMessage(Prefix("fail") + ConfigManager.getSetting("messages.errors.balance-not-enough")
                                .replace("$balance", balanceName));
                    }
                }
                break;
            default:
                break;
        }

        return value;
    }
}
