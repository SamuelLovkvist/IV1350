package se.kth.iv1350.pos.integration;

import org.apache.commons.lang3.StringUtils;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.Receipt;

/**
 * Class representing a receipt.
 */
public class Printer {
    private static final int RECEIPT_WIDTH = 40;

    /**
     * Prints a receipt using info about a sale.
     * @param receipt <code>Receipt</code> object containing info about a sale.
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(StringUtils.rightPad("+", RECEIPT_WIDTH - 1, "-") + "+");

        print("Date and time");
        print(String.valueOf(receipt.getSaleDateTime()));
        print("");
        for(ItemDTO item : receipt.getItemList()) {
            print(item.getName() +  ", qty: " + item.getQuantity() +
                    "    " + (double)Math.round((item.getPrice() * item.getItemVAT()
                    * item.getQuantity()) * 100) / 100 + "SEK");
        }
        print("");
        print("Total: " + (double)Math.round(receipt.getTotalPrice() * 100) / 100
                + "SEK    VAT: " + (double)Math.round(receipt.getTotalVAT() * 100) / 100 + "SEK");
        print("Paid: " + (double)Math.round(receipt.getAmountPaid() * 100) / 100
                + "SEK    Change: " + (double)Math.round(receipt.getChange() * 100) / 100 + "SEK");


        System.out.println(StringUtils.rightPad("+", RECEIPT_WIDTH - 1, "-") + "+");
    }

    /**
     * Prints given input with vertical lines on both sides.
     * @param output String that should be printed.
     */
    private void print(String output) {
        System.out.println(StringUtils.center(StringUtils.center(output, RECEIPT_WIDTH - 2), RECEIPT_WIDTH, "|"));
    }
}
