package jss.advancedchat.utils.version;

import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagList;

public class V_1_8_R1 {

    public ItemStack setSkull(ItemStack item, String id, String textura) {
        net.minecraft.server.v1_8_R1.ItemStack head = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = head.hasTag() ? head.getTag() : new NBTTagCompound();
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
        head.setTag(tag);
        return CraftItemStack.asBukkitCopy(head);
    }
    
    

}
