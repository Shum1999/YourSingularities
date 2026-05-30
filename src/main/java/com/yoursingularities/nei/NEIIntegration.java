package com.yoursingularities.nei;

import com.yoursingularities.YourSingularitiesMod;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.Optional;

@Optional.Interface(iface = "codechicken.nei.api.IConfigureNEI", modid = "NotEnoughItems")
public class NEIIntegration implements IConfigureNEI {
    
    @Override
    public void loadConfig() {
        TemplateRecipeHandler handler = new SingularityRecipeHandler();
        API.registerRecipeHandler(handler);
        API.registerUsageHandler(handler);
    }
    
    @Override
    public String getName() { return "Your Singularities NEI Plugin"; }
    
    @Override
    public String getVersion() { return YourSingularitiesMod.VERSION; }
    
    public static void register() { API.registerNEIGuiHandler(new NEIGuiHandler()); }
}