package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.*;

/**
 * Represents the controller of the program.
 */
public class Controller {
    private AccountingSystem accountingSys;
    private DiscountDatabase discountDB;
    private InventorySystem inventorySys;
    private Printer printer;
    private SaleLog saleLog;
    private Sale sale;

    /**
     * Creates an instance of <code>Controller</code>.
     * @param accountingSys External accounting system.
     * @param discountDB Discount database.
     * @param inventorySys External inventory system.
     * @param printer Printer.
     * @param saleLog Sale log system.
     */
    public Controller(AccountingSystem accountingSys, DiscountDatabase discountDB,
                      InventorySystem inventorySys, Printer printer, SaleLog saleLog) {
        this.accountingSys = accountingSys;
        this.discountDB = discountDB;
        this.inventorySys = inventorySys;
        this.printer = printer;
        this.saleLog = saleLog;
    }

    /**
     * Starts a new sale by making a <code>Sale</code> object.
     */
    public void startNewSale() {
        sale = new Sale();
    }

    /**
     * Updates quantity of the item if it is in the sale.
     * If not, adds item found in <code>InventorySystem</code> to the sale.
     * @param itemID The item's identifier.
     * @param quantity Quantity of the item is 1 unless the cashier manually changes it.
     */
    public void scanItem(String itemID, int quantity) {
        ScannedItem item = new ScannedItem(itemID, quantity);
        if(sale.itemInSale(item))
            sale.increaseItemQuantity(item);
        else
            sale.addItemToSale(inventorySys.inventorySearch(item));
        displaySaleInfo();
    }

    /**
     * Displays information about the current sale.
     */
    private void displaySaleInfo() {
        for(ItemDTO item : sale.getItemList()) {
            System.out.println(item.getName() + ", Qty: " + item.getQuantity() + "           " +
                    (double)Math.round(item.getPrice() * item.getItemVAT() * item.getQuantity() * 100) / 100
                    + ":-");
        }
        System.out.println("Total: " + (double)Math.round(sale.getTotalPrice() * 100) / 100 + "SEK");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Displays the total when all items have been scanned.
     */
    public void endSale() {
        System.out.println("Total: " + (double) Math.round(sale.getTotalPrice() * 100) / 100);
        saleLog.logSale(sale, accountingSys, inventorySys);
    }

    /**
     * Gets a discount from the discount database and applies it.
     * @param customerID Customers identification.
     */
    public void requestDiscount(String customerID) {
        sale.handleDiscount(discountDB.fetchDiscount(customerID));
        System.out.println("New total: " + (double)Math.round(sale.getTotalPrice() * 100) / 100);
    }

    /**
     * Updates amount paid by customer.
     * @param amount Amount paid by customer.
     */
    public void payment(double amount) {
        sale.updateAmountPaid(amount);
        System.out.println("Change: " + (double)Math.round(sale.getChange() * 100) / 100);
    }

    /**
     * Calls the <code>printer</code> that prints a receipt.
     */
    public void printReceipt() {
        printer.printReceipt(sale.getReceipt(sale));
    }
}
