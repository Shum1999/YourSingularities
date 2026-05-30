package com.yoursingularities;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import com.yoursingularities.config.SingularityConfig;
import com.yoursingularities.items.ItemCustomSingularity;
import com.yoursingularities.nei.NEIIntegration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = YourSingularitiesMod.MODID, name = YourSingularitiesMod.NAME, version = YourSingularitiesMod.VERSION)
public class YourSingularitiesMod {
    public static final String MODID = "yoursingularities";
    public static final String NAME = "Your Singularities";
    public static final String VERSION = "1.0";

    public static List<ItemCustomSingularity> singularities = new ArrayList<>();
    public static Configuration config;
    public static boolean isNEILoaded = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "yoursingularities.cfg");
        config = new Configuration(configFile);
        SingularityConfig.loadConfig(config);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        for (SingularityConfig.SingularityEntry entry : SingularityConfig.singularities) {
            ItemCustomSingularity singularity = new ItemCustomSingularity(entry.name, entry.texturePath, entry.color, entry.baseItem);
            singularity.setCreativeTab(CreativeTabs.tabMisc);
            GameRegistry.registerItem(singularity, entry.name);
            singularities.add(singularity);
        }
        
        try {
            Class.forName("codechicken.nei.api.API");
            isNEILoaded = true;
            NEIIntegration.register();
            System.out.println("[YourSingularities] NEI integration registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("[YourSingularities] NEI not found, skipping integration.");
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        for (ItemCustomSingularity singularity : singularities) {
            if (singularity.baseItem != null) {
                GameRegistry.addShapedRecipe(new ItemStack(singularity),
                        "III", "I I", "III",
                        'I', singularity.baseItem);
            }
        }
    }
}