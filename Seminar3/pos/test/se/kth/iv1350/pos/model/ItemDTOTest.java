package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {
    private ItemDTO testItem;

    @BeforeEach
    void setUp() {
        testItem = new ItemDTO("1AB01", "Nocco", 5, 20, 1.12);
    }

    @AfterEach
    void tearDown() {
        testItem = null;
    }
    @Test
    void increaseItemDTOQuantity() {
        ScannedItem scannedTestItem = new ScannedItem("1AB01", 3 );
        testItem.increaseItemDTOQuantity(scannedTestItem);
        int expResult = 8;
        int result = testItem.getQuantity();
        assertEquals(expResult, result, "Quantities of the same value does not match.");
    }

    @Test
    void setQuantity() {
        testItem.setQuantity(7);
        int expResult = 7;
        int result = testItem.getQuantity();
        assertEquals(expResult, result, "Quantities of the same value does not match.");
    }

    @Test
    void getItemID() {
        String expResult = "1AB01";
        String result = testItem.getItemID();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getName() {
        String expResult = "Nocco";
        String result = testItem.getName();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getQuantity() {
        int expResult = 5;
        int result = testItem.getQuantity();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getPrice() {
        double expResult = 20;
        double result = testItem.getPrice();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }

    @Test
    void getItemVAT() {
        double expResult = 1.12;
        double result = testItem.getItemVAT();
        assertEquals(expResult, result, "Expected result did not equal result.");
    }
}