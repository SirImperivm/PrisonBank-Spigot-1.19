package me.sirimperivm.spigot.modules.listeners;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.managers.ConfigManager;
import me.sirimperivm.spigot.assets.others.General;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

@SuppressWarnings("all")
public class ClickListener implements Listener {
    private static ConfigManager conf = Main.getConf();

    private static String Prefix (String id) {
        return General.Prefix(id);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        ClickType click = e.getClick();

        if (e.getView().getTitle().equalsIgnoreCase(ConfigManager.getGui("guis.bankGui.title"))) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);

            String path = "guis.bankGui.items.";

            boolean enabledClose = conf.getGuis().getBoolean(path + "close.enabled");
            boolean enabledMoneyBank = conf.getGuis().getBoolean(path + "money.enabled");
            boolean enabledTokensBank = conf.getGuis().getBoolean(path + "tokens.enabled");
            boolean enabledGemsBank = conf.getGuis().getBoolean(path + "gems.enabled");

            int closeSlot = conf.getGuis().getInt(path + "close.slot");
            int moneyBank = conf.getGuis().getInt(path + "money.slot");
            int tokensBank = conf.getGuis().getInt(path + "tokens.slot");
            int gemsBank = conf.getGuis().getInt(path + "gems.slot");

            if (enabledClose && slot == closeSlot) {
                p.closeInventory();
            }

            String pname = p.getName();
            int waitingTime = conf.getSettings().getInt("settings.waitingTime");

            if (enabledMoneyBank && slot == moneyBank) {
                if (click == ClickType.LEFT) {
                    if (Main.getMm().isBalanceRemovable(p, "LEFT", "money")) {
                        Main.getMm().giveMoneyItem(p, 100);
                    }
                } else if (click == ClickType.RIGHT) {
                    if (Main.getMm().isBalanceRemovable(p, "RIGHT", "money")) {
                        Main.getMm().giveMoneyItem(p, 1000);
                    }
                }
            }

            if (enabledTokensBank && slot == tokensBank) {
                if (click == ClickType.LEFT) {
                    if (Main.getMm().isBalanceRemovable(p, "LEFT", "tokens")) {
                        p.performCommand("tokens withdraw " + 50);
                    } else if (click == ClickType.RIGHT) {
                        if (Main.getMm().isBalanceRemovable(p, "RIGHT", "tokens")) {
                            p.performCommand("tokens withdraw " + 500);
                        }
                    }
                }
            }

            if (enabledGemsBank && slot == gemsBank) {
                if (click == ClickType.LEFT) {
                    if (Main.getMm().isBalanceRemovable(p, "LEFT", "gems")) {
                        p.performCommand("gems withdraw " + 50);
                    } else if (click == ClickType.RIGHT) {
                        if (Main.getMm().isBalanceRemovable(p, "RIGHT", "gems")) {
                            p.performCommand("gems withdraw " + 500);
                        }
                    }
                }
            }
        }
    }
}
