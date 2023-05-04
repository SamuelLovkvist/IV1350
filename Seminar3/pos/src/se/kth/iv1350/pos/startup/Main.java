package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.view.View;


public class Main {
    public static void main(String[] args) {
        AccountingSystem accountingSys = new AccountingSystem();
        InventorySystem inventorySys = new InventorySystem();
        DiscountDatabase discountDB = new DiscountDatabase();
        SaleLog saleLog = new SaleLog();
        Printer printer = new Printer();
        Controller contr = new Controller(accountingSys, discountDB, inventorySys, printer, saleLog);
        View view = new View(contr);

        view.testRun();
    }
}