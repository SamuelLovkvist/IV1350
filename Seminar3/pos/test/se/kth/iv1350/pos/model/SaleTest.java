package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        sale.getItemList().add(new ItemDTO("1AB01", "Nocco", 5, 20, 1.12));
        sale.getItemList().add(new ItemDTO("1AB00", "Monster", 6, 19, 1.06));
    }

    @AfterEach
    void tearDown() {
        sale = null;
    }

    @Test
    void itemInSale() {
        ScannedItem scannedItem = new ScannedItem("1AB00", 4);
        boolean expResult = true;
        boolean result = sale.itemInSale(scannedItem);
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void increaseItemQuantity() {
        ScannedItem scannedItem = new ScannedItem("1AB00", 4);
        sale.increaseItemQuantity(scannedItem);
        int expResult = 10;
        int result = sale.getItemList().get(1).getQuantity();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void addItemToSale() {
        ItemDTO newItem = new ItemDTO("1AB07", "Celsius", 3, 21, 1.25);
        sale.addItemToSale(newItem);
        ItemDTO expResult = sale.getItemList().get(2);
        ItemDTO result = newItem;
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void handleDiscount() {
        sale.updateTotalPrice();
        sale.handleDiscount(0.95);
        double expResult = ((5 * 20 * 1.12) + (6 * 19 * 1.06)) * 0.95;
        double result = sale.getTotalPrice();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void updateTotalPrice() {
        sale.updateTotalPrice();
        double expResult = (5 * 20 * 1.12) + (6 * 19 * 1.06);
        double result = sale.getTotalPrice();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void updateAmountPaid() {
        sale.updateAmountPaid(60.44);
        double expResult = 60.44;
        double result = sale.getAmountPaid();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getItemList() {
        ArrayList<ItemDTO> testList = new ArrayList<>();
        testList.add(new ItemDTO("1AB01", "Nocco", 5, 20, 1.12));
        testList.add(new ItemDTO("1AB00", "Monster", 6, 19, 1.06));
        ArrayList<ItemDTO> expResult = testList;
        ArrayList<ItemDTO> result = sale.getItemList();
        for(int i = 0; i < 2; i++){
            assertEquals(expResult.get(i).getItemID(), result.get(i).getItemID(), "Expected itemIDs did not match.");
            assertEquals(expResult.get(i).getName(), result.get(i).getName(), "Expected names did not match.");
            assertEquals(expResult.get(i).getQuantity(), result.get(i).getQuantity(), "Expected quantities did not match.");
            assertEquals(expResult.get(i).getPrice(), result.get(i).getPrice(), "Expected prices did not match.");
            assertEquals(expResult.get(i).getItemVAT(), result.get(i).getItemVAT(), "Expected itemVATs did not match.");
        }
    }

    @Test
    void getTotalPrice() {
        sale.updateTotalPrice();
        double expResult = (5 * 20 * 1.12) + (6 * 19 * 1.06);
        double result = sale.getTotalPrice();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getAmountPaid() {
        sale.updateAmountPaid(13.37);
        double expResult = 13.37;
        double result = sale.getAmountPaid();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getChange() {
        sale.updateTotalPrice();
        sale.updateAmountPaid(300);
        double expResult = 300 - ((5 * 20 * 1.12) + (6 * 19 * 1.06));
        double result = sale.getChange();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getReceipt() {
//        assertEquals(expResult, result, "Expected result did not equal result.");
    }
}