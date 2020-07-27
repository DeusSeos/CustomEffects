package deusseos.customeffects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Utils {


    public List<String> dashList(List<String> lore) {
        List<String> effectLores = new ArrayList<>();
        for (String s : lore) {
            if (s.contains("-"))
                //Bukkit.broadcastMessage("has a dash!   " + s);
                effectLores.add(ChatColor.stripColor(s));
        }
        return effectLores;
    }

    public void applyEffects(Player receiver, List<String> keys, Hashtable<String, PotionEffect> effectTable) {
        for(String s: keys) {
//            Bukkit.broadcastMessage("checking " + s);
            if (effectTable.isEmpty()) {
                return;
            } else if (!effectTable.containsKey(s)) {
                return;
            } else {
//                Bukkit.broadcastMessage(s);
                receiver.addPotionEffect(effectTable.get(s));

            }
        }
    }

    public void removeEffect(Player receiver, List<String> keys, Hashtable<String, PotionEffect> effectTable) {
        for(String s: keys) {
//            Bukkit.broadcastMessage("checking " + s);
            if (effectTable.isEmpty()) {
                return;
            } else if (!effectTable.containsKey(s)) {
                return;
            } else {
//                Bukkit.broadcastMessage(s);
                receiver.removePotionEffect(effectTable.get(s).getType());

            }
        }
    }
}
