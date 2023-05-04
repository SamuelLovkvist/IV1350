package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void testRun() {
        System.out.println("Start");

        contr.startNewSale();

        contr.scanItem("1AB03", 1);
        contr.scanItem("1AB04", 4);
        contr.scanItem("1AB03", 2);
        contr.scanItem("1AB05", 1);
        contr.scanItem("1AB06", 1);
        contr.scanItem("1AB02", 1);
        contr.endSale();

        contr.requestDiscount("5010");

        contr.payment(4000);
        System.out.println();

        contr.printReceipt();
    }
}
