package deusseos.customeffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class ConfigManip {

    FileConfiguration config = CustomEffects.getPlugin(CustomEffects.class).getConfig();

    // holds a local reference to the config file of the plugin

    public void createConfig(String path) {
        path = path + '.';
        List<String> lore = Arrays.asList("- Curses the Enemy", "- Blinds the Enemy", "- Starves the Enemy", "- Slows the Enemy");
        List<String> effects = Arrays.asList(".Type", ".Duration", ".Amplifier", ".Ambient", ".Particles");
        for (String s : lore) {
            String stringLore;
            stringLore = s;
            config.createSection(path + stringLore);
            config.set(path + stringLore + effects.get(0), "REGENERATION");
            config.set(path + stringLore + effects.get(1), 0);
            config.set(path + stringLore + effects.get(2), 0);
            config.set(path + stringLore + effects.get(3), false);
            config.set(path + stringLore + effects.get(4), false);
        }
    }

    public void storeEffects(String path, Hashtable<String, PotionEffect> effectTable) {

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
            effectTable.put(key, toPotionType(type, duration, amplifier, ambient, particles));
        }
    }

    public PotionEffect toPotionType(String type, int duration, int amplifier, boolean ambient, boolean particles) {
        PotionEffectType effectType;
        effectType = PotionEffectType.getByName(type);
        return new PotionEffect(effectType, duration, amplifier, ambient, particles);
    }

    public void displayConfig() {
        for (String key : Objects.requireNonNull(config.getConfigurationSection("Melee.")).getKeys(true)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + key);
        }
    }

}

