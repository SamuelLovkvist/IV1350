package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.ScannedItem;

import java.util.ArrayList;

/**
 * Represents the external inventory system.
 */
public class InventorySystem {
    private ArrayList<ItemDTO> itemDTOs;

    /**
     * Creates an instance of <code>InventorySystem</code>.
     */
    public InventorySystem() {
        itemDTOs = new ArrayList<>();
        itemDTOs.add(new ItemDTO("1AB02", "Gris 125kg", 1, 3249.98, 1.06));
        itemDTOs.add(new ItemDTO("1AB03", "Banan", 8, 8.49, 1.12));
        itemDTOs.add(new ItemDTO("1AB04", "Avocado", 4, 15, 1.25));
        itemDTOs.add(new ItemDTO("1AB05", "Chips", 2, 20.98, 1.12));
        itemDTOs.add(new ItemDTO("1AB06", "Mössa", 2, 379, 1.06));
    }

    /**
     * Searches the inventory for the scanned item.
     * @param scannedItem Scanned item.
     * @return Either an <code>ItemDTO</code> or null.
     */
    public ItemDTO inventorySearch(ScannedItem scannedItem) {
        ItemDTO newItem = null;
        for(ItemDTO item : itemDTOs) {
            if(item.getItemID().equalsIgnoreCase(scannedItem.getItemID())) {
                newItem = new ItemDTO(item.getItemID(), item.getName(),
                        scannedItem.getQuantity(), item.getPrice(), item.getItemVAT());
                return newItem;
            }
        }
        System.out.println("Invalid ItemID");
        return null;
    }

    /**
     * Updates the inventory based on the items in the sale.
     * @param sale <code>Sale</code> object containing info about the sale.
     */
    public void updateInventory(Sale sale) {

        for(ItemDTO saleItem : sale.getItemList()) {
            for (ItemDTO invItem : itemDTOs) {
                if (invItem.getItemID().equalsIgnoreCase(saleItem.getItemID())) {
                    invItem.setQuantity(invItem.getQuantity() - saleItem.getQuantity());
                    if (invItem.getQuantity() < 0) {
                        System.out.println("Not enough \"" + invItem.getName() + "\" in Inventory");
                    }
                }
            }
        }
    }
}
