package se.kth.iv1350.pos.model;

/**
 * Class representing a scanned item.
 */
public class ScannedItem {
    private String itemID;
    private int quantity;

    /**
     * Creates an instance of <code>ScannedItem</code>.
     * @param itemID Identifier of the item.
     * @param quantity Quantity of the item.
     */
    public ScannedItem(String itemID, int quantity) {
       this.itemID = itemID;
       this.quantity = quantity;
   }

    /**
     * Returns identifier of the item.
     * @return Identifier of the item.
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Returns quantity of the item.
     * @return Quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }
}
