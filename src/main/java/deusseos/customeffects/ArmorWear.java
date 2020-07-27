package deusseos.customeffects;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class ArmorWear implements Listener {

    Utils utils = new Utils();

    @EventHandler
    public void armorEquip(ArmorEquipEvent e) {
        Bukkit.broadcastMessage("FIRED");
        if (e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR) {
            if (e.getNewArmorPiece().getItemMeta().hasLore()) {
                Bukkit.broadcastMessage(e.getNewArmorPiece().toString());
                utils.applyEffects(e.getPlayer(), utils.dashList(e.getNewArmorPiece().getItemMeta().getLore()), CustomEffects.armorEffects);
            }
        } else if (e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR) {
            Bukkit.broadcastMessage("Stop stripping me");
            if (e.getOldArmorPiece().getItemMeta().hasLore()) {
                Bukkit.broadcastMessage(e.getOldArmorPiece().toString());
                utils.removeEffect(e.getPlayer(), utils.dashList(e.getOldArmorPiece().getItemMeta().getLore()), CustomEffects.armorEffects);
            }
        }


    }
}



    /*
    @EventHandler
    public void armorSlotInteract(InventoryClickEvent event) {
        Bukkit.broadcastMessage("" + event.getSlot());
        //if not player inventory
        if (event.getClickedInventory().getType() != InventoryType.PLAYER)
            return;
        Bukkit.broadcastMessage("OMG A PLAYER");
        //always player inventory and armor slots are clicked
        if (event.getSlot() >= 36 && event.getSlot() <= 39) {
            PlayerInventory inventory = (PlayerInventory) event.getClickedInventory();
            ItemStack[] armorContents = inventory.getArmorContents();
            Bukkit.broadcastMessage("He touched my armor");
            Bukkit.broadcastMessage("He touchie my" + event.getCurrentItem().toString());
            for (ItemStack stack : armorContents) {
                if (stack != null) {
                    Bukkit.broadcastMessage(stack.toString());
                    if (stack.getItemMeta().hasLore()) {
                        utils.applyEffects((Player) event.getWhoClicked(), utils.dashList(stack.getItemMeta().getLore()), CustomEffects.armorEffects);
                    }
                }
            }
        }


        //Check if dealing with armor slot first

        if (event.getSlotType() != InventoryType.SlotType.ARMOR)
            return;
        //check if armor slot is null or not
        if (event.getClickedInventory().getItem(event.getSlot()) == null)
            return;
        ItemStack itemStack = event.getClickedInventory().getItem(event.getSlot());
        Material[] materials = {Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE};
        if (ArrayUtils.contains(materials, itemStack.getType())) {
            Bukkit.broadcastMessage(event.getClick().toString());
            Bukkit.broadcastMessage("" + event.getSlot());
            Bukkit.broadcastMessage(event.getCurrentItem().toString());
        }


    }
}
*/