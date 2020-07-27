package deusseos.customeffects;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class MeleeReset implements Listener {

    @EventHandler
    public void reset(EntityDamageEvent e){
        if (e.getEntityType() == EntityType.PLAYER){
            Player player = (Player) e.getEntity();
            player.setNoDamageTicks(1);
        }
    }


}
