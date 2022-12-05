package jss.advancedchat.utils.interfaces;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public interface InventoryHelper {
    int getSize();

    void setSize(int paramInt);

    String getTitle();

    void setTitle(String paramString);

    int getRow();

    void setRow(int paramInt);

    int getDefaultSize();

    void setDefaultSize(int paramInt);

    void setLegacyItemRow(Inventory paramInventory, Material paramMaterial, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString);

    void setItemRow(Inventory paramInventory, Material paramMaterial, int paramInt1, int paramInt2, int paramInt3, String paramString);

    boolean CheckVersion();

    boolean setGlowItem(ItemMeta paramItemMeta);

    String getId();

    void setId(String paramString);

    String getTexture();

    void setTexture(String paramString);

    void open(Player paramPlayer, Inventory paramInventory);
}
