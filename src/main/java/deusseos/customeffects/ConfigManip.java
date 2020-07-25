package deusseos.customeffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConfigManip {

    FileConfiguration config = CustomEffects.getPlugin(CustomEffects.class).getConfig();

    // holds a local reference to the config file of the plugin

    public void createMeleeDefaults() {
        String path = "Melee.";
        List<String> lores = Arrays.asList("- Curses the Enemy", "- Blinds the Enemy", "- Starves the Enemy", "- Slows the Enemy");
        List<String> effects = Arrays.asList(".Type", ".Duration", ".Amplifier", ".Ambient", ".Particles");
        for (String s : lores) {
            String lore;
            lore = s;
            config.createSection(path + lore);
            config.set(path + lore + effects.get(0), "REGENERATION");
            config.set(path + lore + effects.get(1), 0);
            config.set(path + lore + effects.get(2), 0);
            config.set(path + lore + effects.get(3), false);
            config.set(path + lore + effects.get(4), false);

        }

    }

    public void storeMeleeEffects() {
        String path = "Melee";
        String type;
        int duration;
        int amplifier;
        boolean ambient;
        boolean particles;
        ConfigurationSection section = config.getConfigurationSection(path);
        for (String key : Objects.requireNonNull(Objects.requireNonNull(section).getKeys(false))) {
            List<String> effects = Arrays.asList(".Type", ".Duration", ".Amplifier", ".Ambient", ".Particles");
            type = section.getString(key + effects.get(0));
            duration = section.getInt(key + ".Duration");
            amplifier = section.getInt(key + ".Amplifier");
            ambient = section.getBoolean(key + ".Ambient");
            particles = section.getBoolean(key + ".Particles");
            CustomEffects.effectDefaults.put(key, toPotionType(type, duration, amplifier, ambient, particles));
        }
    }

    public PotionEffect toPotionType(String type, int duration, int amplifier, boolean ambient, boolean particles) {
        PotionEffectType effectType;
        effectType = PotionEffectType.getByName(type);
        return new PotionEffect(effectType, duration, amplifier, ambient, particles);
    }

    /*
    public void createPotions(){
        ConfigurationSection section = config.getConfigurationSection("Potion");
        for (String key : Objects.requireNonNull(section.getKeys(false))) {
            Bukkit.getConsoleSender().sendMessage(key);
            int duration = section.getInt(key + ".Duration");
            int amplifier = section.getInt(key + ".Amplifier");
            boolean ambient = section.getBoolean(key + ".Ambient");
            int particles = section.getInt(key + ".Particles");
            Bukkit.getConsoleSender().sendMessage(String.format("%d %d %b %b", duration, amplifier, ambient, particles));

        }
    }

     */
    public void displayConfig() {
        for (String key : config.getConfigurationSection("Melee.").getKeys(true)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + key);
        }
    }

}

