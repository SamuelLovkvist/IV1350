package se.kth.iv1350.pos.model;


/**
 * Item data transfer object, groups together variables for items.
 */
public class ItemDTO {
    private String itemID;
    private String name;
    private int quantity;
    private double price;
    private double itemVAT;

    /**
     * Creates an instance of <code>ItemDTO</code>.
     * @param itemID Identifier of the item.
     * @param name Name of the item.
     * @param quantity Quantity of the item.
     * @param price Price of the item.
     * @param itemVAT VAT rate of the item. (1.25, 1.12, 1.6)
     */
    public ItemDTO(String itemID, String name, int quantity, double price, double itemVAT) {
        this.itemID = itemID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.itemVAT = itemVAT;
    }

    /**
     * Increases the quantity of an item.
     * @param scannedItem Object containing information about the scanned item.
     */
    public void increaseItemDTOQuantity(ScannedItem scannedItem) {
        quantity += scannedItem.getQuantity();
    }

    /**
     * Set the quantity of an item.
     * @param quantity Quantity of the item.
     */
    public void setQuantity(int quantity) {
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
     * Returns name of the item.
     * @return Name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns quantity of the item.
     * @return Quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns price of the item.
     * @return Price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns VAT rate of the item.
     * @return VAT rate of the item.
     */
    public double getItemVAT() {
        return itemVAT;
    }
}
