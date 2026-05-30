package com.yoursingularities.nei;

import codechicken.nei.api.INEIGuiHandler;
import cpw.mods.fml.common.Optional;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import java.awt.Point;
import java.util.List;

@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")
public class NEIGuiHandler implements INEIGuiHandler {
    @Override public Iterable<Integer> getItemSpawnSlots(GuiContainer gui, ItemStack item) { return null; }
    @Override public List<PositionedStack> getRecipeTransferRect(GuiContainer gui, Point offset) { return null; }
    @Override public GuiContainer getRecipeGui(GuiContainer gui) { return null; }
    @Override public boolean handleDragNDrop(GuiContainer gui, int mousex, int mousey, ItemStack draggedStack, int button) { return false; }
    @Override public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h) { return false; }
}