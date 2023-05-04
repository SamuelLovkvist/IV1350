package se.kth.iv1350.pos.integration;

/**
 * Represents the discount database.
 */
public class DiscountDatabase {

    /**
     * Finds a discount for a specified customer.
     * @param customerID Identification of the customer.
     * @return Discount.
     */
    public double fetchDiscount(String customerID) {
        return 0.95;
    }
}
