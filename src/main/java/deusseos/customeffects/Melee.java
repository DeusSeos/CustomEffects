package deusseos.customeffects;

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

    Utils utils = new Utils();

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
                    utils.applyEffects(receivingPlayer, utils.dashList(metaLore), CustomEffects.meleeEffects);

                } else {
                }


            }

        }
    }


}
