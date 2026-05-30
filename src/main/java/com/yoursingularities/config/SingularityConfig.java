package com.yoursingularities.config;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import java.util.ArrayList;
import java.util.List;

public class SingularityConfig {
    public static List<SingularityEntry> singularities = new ArrayList<>();

    public static class SingularityEntry {
        public String name;
        public String texturePath;
        public int color;
        public Item baseItem;
        public SingularityEntry(String name, String texturePath, int color, Item baseItem) {
            this.name = name;
            this.texturePath = texturePath;
            this.color = color;
            this.baseItem = baseItem;
        }
    }

    public static void loadConfig(Configuration config) {
        config.load();
        
        String[] singularitiesArray = config.get("singularities", "list", new String[]{
            "diamond:yoursingularities:diamond_singularity:0x33E0FF:minecraft:diamond",
            "emerald:yoursingularities:emerald_singularity:0x33FF77:minecraft:emerald",
            "gold:yoursingularities:gold_singularity:0xFFDD33:minecraft:gold_ingot"
        }).getStringList();

        for (String entry : singularitiesArray) {
            String[] parts = entry.split(":");
            if (parts.length >= 5) {
                String name = parts[0];
                String texturePath = parts[1];
                int color = (int) Long.parseLong(parts[2].replace("0x", ""), 16);
                String baseItemMod = parts[3];
                String baseItemName = parts[4];
                Item baseItem = (Item) Item.itemRegistry.getObject(baseItemMod + ":" + baseItemName);
                if (baseItem != null) {
                    singularities.add(new SingularityEntry(name, texturePath, color, baseItem));
                }
            }
        }

        if (config.hasChanged()) config.save();
    }
}