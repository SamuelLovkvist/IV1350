package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the sale of a customer.
 */
public class Sale {
    private LocalDateTime saleDateTime;
    private ArrayList<ItemDTO> itemList;
    private double totalPrice;
    private double amountPaid;
    private double change;
    private Receipt receipt;

    /**
     * Creates an instance of <code>Sale</code>.
     */
    public Sale() {
        saleDateTime = LocalDateTime.now();
        itemList = new ArrayList<>();
    }

    /**
     * Checks if the scanned item has a matching <code>itemID</code> in the <code>itemList</code>.
     * @param scannedItem The scanned item.
     * @return True if item is found in the current sale, False if not found.
     */
    public boolean itemInSale(ScannedItem scannedItem) {
        for(ItemDTO item : itemList) {
            if(item.getItemID().equalsIgnoreCase(scannedItem.getItemID()))
                return true;
        }
        return false;
    }

    /**
     * Increases the quantity of an item in current sale.
     * @param scannedItem Object containing information about the scanned item.
     */
    public void increaseItemQuantity(ScannedItem scannedItem) {
        for(ItemDTO item : itemList) {
            if(item.getItemID().equalsIgnoreCase(scannedItem.getItemID())){
                item.increaseItemDTOQuantity(scannedItem);
                break;
            }
        }
        updateTotalPrice();
    }

    /**
     * Adds an item to the current sale.
     * @param newItem Item that is added.
     */
    public void addItemToSale(ItemDTO newItem) {
        itemList.add(newItem);
        updateTotalPrice();
    }

    /**
     * Updates price of the sale by applying the discount.
     * @param discount Discount that should be applied.
     */
    public void handleDiscount(double discount) {
        totalPrice *= discount;
    }

    /**
     * Updates the total price.
     */
    public void updateTotalPrice(){
        totalPrice = 0;
        for(ItemDTO item : itemList) {
            totalPrice += item.getPrice() * item.getItemVAT() * item.getQuantity();
        }
    }

    /**
     * Updates amount paid by customer and calculates the change.
     * @param amount Amount paid by customer.
     */
    public void updateAmountPaid(double amount) {
        amountPaid = amount;
        change = amountPaid - totalPrice;
    }

    /**
     * Returns date and time of sale.
     * @return Date and time of sale.
     */
    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }

    /**
     * Returns list of items in the sale.
     * @return List of items in the sale.
     */
    public ArrayList<ItemDTO> getItemList() {
        return itemList;
    }

    /**
     * Returns total price of the sale.
     * @return Total price of the sale.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns amount paid by the customer.
     * @return Amount paid by the customer.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Returns change that customer receives.
     * @return Change that customer receives.
     */
    public double getChange() {
        return change;
    }

    /**
     * Creates a receipt based on a sale.
     * @param sale <code>Sale</code> object containing info about a sale.
     * @return <code>Receipt</code> object.
     */
    public Receipt getReceipt(Sale sale) {
        receipt = new Receipt(sale);
        return receipt;
    }
}