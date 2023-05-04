package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Sale;

import java.util.ArrayList;

/**
 * Class representing a sale log.
 */
public class SaleLog {
    private ArrayList<Sale> saleLogList;

    /**
     * Creates an instance of <code>SaleLog</code>.
     */
    public SaleLog() {
        saleLogList = new ArrayList<>();
    }

    /**
     * Stores the information about a sale in an <code>ArrayList</code>.,
     * @param sale Sale object containing info about a sale.
     * @param accountingSys External accounting system.
     * @param inventorySys External inventory system.
     */
    public void logSale(Sale sale, AccountingSystem accountingSys, InventorySystem inventorySys) {
        saleLogList.add(sale);
        inventorySys.updateInventory(sale);
        accountingSys.updateAccounting(sale);
    }
}
