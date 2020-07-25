package deusseos.customeffects;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.util.Hashtable;

/* This is the main class which shall contain only calls to the different supporting classes in order to fetch, store, and apply effects*/
public final class CustomEffects extends JavaPlugin {


    public static Hashtable<String, PotionEffect> effectDefaults = new Hashtable<>();


    public boolean isConfigExists(){ // returns true or false based on existence of the config file.
        File configFile = new File(this.getDataFolder(), "config.yml");
        return  configFile.exists(); // this will tell you if the file exists.
    }

    public void loadConfig(){
        Bukkit.getConsoleSender().sendMessage("Creating config");
        ConfigManip configManip = new ConfigManip();
        getConfig().options().copyDefaults(true);
        configManip.createMeleeDefaults();
        saveConfig();
    }

    @Override
    public void onEnable() {
        /* Section adds a config if it doesn't exist*/
        if (isConfigExists()) Bukkit.getConsoleSender().sendMessage("Config exists");
        else loadConfig();

        //Store our configs to a hashtable for easier and faster access
        ConfigManip config = new ConfigManip();
        config.storeMeleeEffects();
        Bukkit.getConsoleSender().sendMessage(effectDefaults.toString());

        saveConfig();




        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        effectDefaults.clear();
        // Plugin shutdown logic
    }
}
