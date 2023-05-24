package me.sirimperivm.spigot.assets.managers;

import me.sirimperivm.spigot.Main;
import me.sirimperivm.spigot.assets.others.General;
import me.sirimperivm.spigot.assets.utils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("all")
public class GuiManager {

    private static ConfigManager conf = Main.getConf();

    public Inventory bankGui() {
        String title = ConfigManager.getGui("guis.bankGui.title");
        int size = conf.getGuis().getInt("guis.bankGui.size");

        Inventory inv = Bukkit.createInventory(null, size, title);
        String path = "guis.bankGui.items.";

        boolean enabledClose = conf.getGuis().getBoolean(path + "closeMenu.enabled");
        boolean moneyEnabled = conf.getGuis().getBoolean(path + "moneyBank.enabled");
        boolean tokensEnabled = conf.getGuis().getBoolean(path + "tokensBank.enabled");
        boolean gemsEnabled = conf.getGuis().getBoolean(path + "gemsBank.enabled");

        int closeSlot = conf.getGuis().getInt(path + "closeMenu.slot");
        int moneySlot = conf.getGuis().getInt(path + "moneyBank.slot");
        int tokensSlot = conf.getGuis().getInt(path + "tokensBank.slot");
        int gemsSlot = conf.getGuis().getInt(path + "gemsBank.slot");

        ItemStack close = new ItemStack(Material.getMaterial(conf.getGuis().getString(path + "close.material")));
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ConfigManager.getGui(path + "close.display-name"));
        closeMeta.setLore(General.lore(conf.getGuis().getStringList(path + "close.lore")));
        closeMeta.addEnchant(Enchantment.DURABILITY, 0, true);
        closeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        close.setItemMeta(closeMeta);

        ItemStack money = new ItemStack(Material.getMaterial(conf.getGuis().getString(path + "money.material")));
        ItemMeta moneyMeta = money.getItemMeta();
        moneyMeta.setDisplayName(ConfigManager.getGui(path + "money.display-name"));
        moneyMeta.setLore(General.lore(conf.getGuis().getStringList(path + "money.lore")));
        moneyMeta.addEnchant(Enchantment.DURABILITY, 0, true);
        moneyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        money.setItemMeta(moneyMeta);

        ItemStack tokens = new ItemStack(Material.getMaterial(conf.getGuis().getString(path + "tokens.material")));
        ItemMeta tokensMeta = tokens.getItemMeta();
        tokensMeta.setDisplayName(ConfigManager.getGui(path + "tokens.display-name"));
        tokensMeta.setLore(General.lore(conf.getGuis().getStringList(path + "tokens.lore")));
        tokensMeta.addEnchant(Enchantment.DURABILITY, 0, true);
        tokensMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tokens.setItemMeta(tokensMeta);

        ItemStack gems = new ItemStack(Material.getMaterial(conf.getGuis().getString(path + "gems.material")));
        ItemMeta gemsMeta = gems.getItemMeta();
        gemsMeta.setDisplayName(ConfigManager.getGui(path + "gems.display-name"));
        gemsMeta.setLore(General.lore(conf.getGuis().getStringList(path + "gems.lore")));
        gemsMeta.addEnchant(Enchantment.DURABILITY, 0, true);
        gemsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        gems.setItemMeta(gemsMeta);

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(Colors.text("&0*"));
        filler.setItemMeta(fillerMeta);

        if (enabledClose) {
            inv.setItem(closeSlot, close);
        }

        if (moneyEnabled) {
            inv.setItem(moneySlot, money);
        }

        if (tokensEnabled) {
            inv.setItem(tokensSlot, tokens);
        }

        if (gemsEnabled) {
            inv.setItem(gemsSlot, gems);
        }

        for (int i=0; i<size; i++) {
            if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                inv.setItem(i, filler);
            }
        }

        return inv;
    }
}
