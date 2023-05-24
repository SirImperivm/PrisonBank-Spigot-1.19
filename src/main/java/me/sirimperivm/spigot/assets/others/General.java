package me.sirimperivm.spigot.assets.others;

import me.sirimperivm.spigot.assets.managers.ConfigManager;
import me.sirimperivm.spigot.assets.utils.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class General {

    public static String Prefix(String id) {
        return ConfigManager.getSetting("messages.prefixes." + id);
    }

    public static List<String> lore(List<String> l) {
        List<String> cL = new ArrayList<>();
        for (String li : l) {
            cL.add(Colors.text(li));
        }
        return cL;
    }

    public int getKeyFromValue(Map hm, String v) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(v)) {
                return (int) o;
            }
        }
        return 0;
    }
}
