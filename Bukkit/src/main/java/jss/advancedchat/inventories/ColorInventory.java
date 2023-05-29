package jss.advancedchat.inventories;

import com.cryptomorin.xseries.XMaterial;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.MessageUtils;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ColorInventory {

    private Gui gui;

    public ColorInventory(){
        createInv();
    }

    private void createInv(){

        gui = Gui.gui()
                .title(MessageUtils.colorize("&cTest Color"))
                .type(GuiType.CHEST)
                .create();


        assert XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial() != null;
        gui.getFiller().fillBorder(ItemBuilder.from(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()).asGuiItem());

        gui.setDefaultTopClickAction( event -> {
            if(event.getCurrentItem() != null){
                Logger.debug("Slot is empty: " + event.getSlot());
            }else{
                Logger.debug("This slot has: " + event.getSlot() + " | " + Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName());
            }
        });

        assert XMaterial.BELL.parseMaterial() != null;
        GuiItem item = ItemBuilder.from(XMaterial.BELL.parseMaterial()).asGuiItem(event -> {
            if (event.isLeftClick()) {
                Logger.debug("Is left click in " + event.getSlot());
            } else if (event.isRightClick()) {
                Logger.debug("Is right click in " + event.getSlot());
            }
        });

        gui.setItem(15, item);

    }

    public void open(Player player){
        gui.open(player);
    }
}
