package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Object representing a receipt.
 */
public class Receipt {
    private LocalDateTime saleDateTime;
    private ArrayList<ItemDTO> itemList;
    private double amountPaid;
    private double change;
    private double totalPrice;
    private double totalVAT;


    /**
     * Creates an instance of <code>Receipt</code>.
     * @param sale <code>Sale</code> object containing info about a sale.
     */
    public Receipt(Sale sale) {
        saleDateTime = sale.getSaleDateTime();
        itemList = sale.getItemList();
        amountPaid = sale.getAmountPaid();
        change = sale.getChange();
        totalPrice = sale.getTotalPrice();
        totalVAT = calculateTotalVAT(sale);
    }

    /**
     * Calculates the total VAT of the sale.
     * @param sale <code>Sale</code> object containing info about a sale.
     * @return Total VAT of the sale.
     */
    private double calculateTotalVAT(Sale sale) {
        double vat = 0;
        for(ItemDTO item : sale.getItemList())
            vat += (item.getPrice() * (item.getItemVAT() - 1));
        return vat;
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
     * Returns total price of the sale.
     * @return Total price of the sale.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns VAT of the sale.
     * @return VAT of the sale.
     */
    public double getTotalVAT() {
        return totalVAT;
    }
}
