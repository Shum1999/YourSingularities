package com.yoursingularities.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import com.yoursingularities.YourSingularitiesMod;
import com.yoursingularities.items.ItemCustomSingularity;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SingularityRecipeHandler extends TemplateRecipeHandler {
    
    private static final ResourceLocation backgroundGui = new ResourceLocation(
        YourSingularitiesMod.MODID, "textures/gui/nei_background.png");
    
    @Override
    public String getRecipeName() { return "Singularity Recipes"; }
    @Override
    public String getGuiTexture() { return backgroundGui.toString(); }
    @Override
    public String getOverlayIdentifier() { return "singularities"; }
    
    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("item") && results.length > 0 && results[0] instanceof ItemStack) {
            loadCraftingRecipes((ItemStack) results[0]);
        } else if (outputId.equals("singularities")) {
            for (ItemCustomSingularity singularity : YourSingularitiesMod.singularities) {
                arecipes.add(new CachedSingularityRecipe(singularity));
            }
        }
    }
    
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        if (result.getItem() instanceof ItemCustomSingularity) {
            arecipes.add(new CachedSingularityRecipe((ItemCustomSingularity) result.getItem()));
        }
    }
    
    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (ItemCustomSingularity singularity : YourSingularitiesMod.singularities) {
            if (singularity.baseItem != null && 
                NEIServerUtils.areStacksSameType(ingredient, new ItemStack(singularity.baseItem))) {
                arecipes.add(new CachedSingularityRecipe(singularity));
            }
        }
    }
    
    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(68, 24, 176, 0, 40, 18, 40, 0);
    }
    
    public class CachedSingularityRecipe extends CachedRecipe {
        private final ItemCustomSingularity singularity;
        private final PositionedStack result;
        private final List<PositionedStack> ingredients;
        
        public CachedSingularityRecipe(ItemCustomSingularity singularity) {
            this.singularity = singularity;
            this.result = new PositionedStack(new ItemStack(singularity), 119, 24);
            this.ingredients = new ArrayList<>();
            
            if (singularity.baseItem != null) {
                ItemStack baseStack = new ItemStack(singularity.baseItem);
                baseStack.stackSize = 64;
                int[] xPositions = {18, 36, 54, 18, 36, 54, 18, 36, 54};
                int[] yPositions = {6, 6, 6, 24, 24, 24, 42, 42, 42};
                for (int i = 0; i < 9; i++) {
                    ingredients.add(new PositionedStack(baseStack, xPositions[i], yPositions[i]));
                }
            }
        }
        
        @Override
        public PositionedStack getResult() { return result; }
        @Override
        public List<PositionedStack> getIngredients() { return getCycledIngredients(cycleticks / 20, ingredients); }
    }
}