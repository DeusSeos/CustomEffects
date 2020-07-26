package deusseos.customeffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Melee implements Listener {



    public List<String> dashList(List<String> lore) {
        List<String> effectLores = new ArrayList<>();
        for (String s : lore) {
            if (s.contains("-"))
                Bukkit.broadcastMessage("has a dash!   " + s);
                effectLores.add(ChatColor.stripColor(s));
        }
        return effectLores;
    }

    public void applyEffects(Player receiver, List<String> keys) {
        for(String s: keys) {
//            Bukkit.broadcastMessage("checking " + s);
            if (CustomEffects.effectDefaults.isEmpty()) {
                return;
            } else if (!CustomEffects.effectDefaults.containsKey(s)) {
                return;
            } else {
//                Bukkit.broadcastMessage(s);
                receiver.addPotionEffect(CustomEffects.effectDefaults.get(s));
            }
        }
    }

    /*
    public String[] metaSplitter (List<String> metaLore){
        String[] lines = metaLore.get(0).split("\n");
        Bukkit.broadcastMessage(metaLore.get(0));
        Bukkit.broadcastMessage("Spacer");
        //for(String s: lines)
            Bukkit.broadcastMessage(lines[0]);
    }
    */

    @EventHandler
    public void meleeHit(EntityDamageByEntityEvent event) {
        Entity receiver = event.getEntity();
        Entity attacker = event.getDamager();
        //Bukkit.getConsoleSender().sendMessage("EEEEEE");
        //Bukkit.broadcastMessage("tea");
        if (receiver.getType() != EntityType.PLAYER) {
        }
        else {
            //Bukkit.broadcastMessage("FUCK");e
            //determined that the receiving entity is a player
            Player receivingPlayer = (Player) receiver;
            //determine type attacker is
            if (attacker.getType() != EntityType.PLAYER) {
                //Attacker is not a player
                //TODO parse inventory of mob to determine main hand item
            } else {
                // Bukkit.broadcastMessage("GOD THAT HURTS");
                //Attacker is a player
                Player attackingPlayer = (Player) attacker;

                //Fetch player inventory and determine mainhand equipment || Reasoning is that melee can only be mainhand
                ItemStack mainHand = attackingPlayer.getInventory().getItemInMainHand();
                if (mainHand.hasItemMeta() && mainHand.getItemMeta().hasLore()) {
                    ItemMeta meta = mainHand.getItemMeta();
                    List<String> metaLore = meta.getLore();
                    applyEffects(receivingPlayer, dashList(metaLore));

                } else {
                }


            }

        }
    }


}
