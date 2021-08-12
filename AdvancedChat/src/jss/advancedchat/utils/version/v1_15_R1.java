package jss.advancedchat.utils.version;

import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagList;

public class v1_15_R1 {

    public ItemStack setSkull(ItemStack item, String id, String textura) {
        net.minecraft.server.v1_15_R1.ItemStack cabeza = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = cabeza.hasTag() ? cabeza.getTag() : new NBTTagCompound();
        NBTTagCompound skullOwnerCompound = new NBTTagCompound();
        NBTTagCompound propiedades = new NBTTagCompound();
        NBTTagList texturas = new NBTTagList();
        NBTTagCompound texturasObjeto = new NBTTagCompound();
        texturasObjeto.setString("Value", textura);
        texturas.add(texturasObjeto);
        propiedades.set("textures", texturas);
        skullOwnerCompound.set("Properties", propiedades);
        skullOwnerCompound.setString("Id", id);
        tag.set("SkullOwner", skullOwnerCompound);
        cabeza.setTag(tag);
        return CraftItemStack.asBukkitCopy(cabeza);
    }

}
