package me.sirimperivm.spigot.modules.listeners;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.managers.ConfigManager;
import me.sirimperivm.spigot.assets.others.General;
import me.sirimperivm.spigot.assets.utils.Colors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("all")
public class InteractionListener implements Listener {

    private static ConfigManager conf = Main.getConf();

    private static String Prefix(String id) {
        return General.Prefix(id);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(Colors.text("&a&lSoldi"))
            && p.getInventory().getItemInMainHand().getType().equals(Material.getMaterial(conf.getSettings().getString("settings.moneyItems.material")))
            && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 15) {
                double price = Double.parseDouble(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0));
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                Main.getVm().getEcon().depositPlayer(p, price);
            }
        }
    }
}
