package Entities;

public class Quotation {
    private double subtotal;
    private double shippingCost;

    public Quotation(double subtotal, double shippingCost) {
        this.subtotal = subtotal;
        this.shippingCost = shippingCost;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getTotal() {
        return subtotal + shippingCost;
    }

}
