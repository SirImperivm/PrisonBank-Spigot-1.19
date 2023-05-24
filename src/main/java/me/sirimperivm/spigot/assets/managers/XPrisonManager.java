package me.sirimperivm.spigot.assets.managers;

import dev.drawethree.xprison.XPrison;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("all")
public class XPrisonManager {

    public static long getTokens(Player p) {
        long tokens = XPrison.getInstance().getTokens().getTokensManager().getPlayerTokens(p);
        return tokens;
    }

    public static long getGems(Player p) {
        long gems = XPrison.getInstance().getGems().getGemsManager().getPlayerGems(p);
        return gems;
    }

    public static void takeTokens(Player p, long tokens) {
        long balance = getTokens(p);
        XPrison.getInstance().getTokens().getTokensManager().setTokens(p, balance-tokens, Bukkit.getConsoleSender());
    }

    public static void takeGems(Player p, long gems) {
        long balance = getGems(p);
        XPrison.getInstance().getGems().getGemsManager().setGems(p, balance-gems, Bukkit.getConsoleSender());
    }
}
