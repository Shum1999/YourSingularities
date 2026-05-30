package com.yoursingularities.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCustomSingularity extends Item {
    private final String singularityName;
    private final String texturePath;
    private final int color;
    public final Item baseItem;
    private IIcon icon;

    public ItemCustomSingularity(String name, String texturePath, int color, Item baseItem) {
        this.singularityName = name;
        this.texturePath = texturePath;
        this.color = color;
        this.baseItem = baseItem;
        this.setUnlocalizedName("singularity." + name);
        this.setMaxStackSize(64);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.icon = register.registerIcon(texturePath);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return this.color;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String name = singularityName.substring(0, 1).toUpperCase() + singularityName.substring(1);
        return name + " Singularity";
    }
}