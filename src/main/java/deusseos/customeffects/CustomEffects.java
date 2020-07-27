package deusseos.customeffects;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;


import java.io.File;
import java.util.Hashtable;

/* This is the main class which shall contain only calls to the different supporting classes in order to fetch, store, and apply effects*/
public final class CustomEffects extends JavaPlugin {

    public static Hashtable<String, PotionEffect> meleeEffects = new Hashtable<>();
    public static Hashtable<String, PotionEffect> armorEffects = new Hashtable<>();


    public boolean isConfigExists(){ // returns true or false based on existence of the config file.
        File configFile = new File(this.getDataFolder(), "config.yml");
        return  configFile.exists(); // this will tell you if the file exists.
    }

    public void loadConfig(){
        Bukkit.getConsoleSender().sendMessage("Creating config");
        ConfigManip configManip = new ConfigManip();
        getConfig().options().copyDefaults(true);
        configManip.createConfig("Melee");
        configManip.createConfig("Armor");
        saveConfig();
    }

    @Override
    public void onEnable() {
        /* adds a config if it doesn't exist*/
        if (!isConfigExists()) loadConfig();

        //Store our configs to a hashtable for easier and faster access
        ConfigManip config = new ConfigManip();
        config.storeEffects("Melee", meleeEffects);
        config.storeEffects("Armor", armorEffects);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MeleeReset(), this);
        pluginManager.registerEvents(new Melee(), this);
        pluginManager.registerEvents(new ArmorWear(), this);
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        meleeEffects.clear();
        // Plugin shutdown logic
    }
}
