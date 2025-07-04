package Entities;

public class Invoice {
    private String invoiceNumber;
    private Customer customer;
    private String date;
    private Quotation quotation;
    public Invoice(String invoiceNumber, Customer customer, String date, Quotation quotation) {
        this.invoiceNumber = invoiceNumber;
        this.customer = customer;
        this.date = date;
        this.quotation = quotation;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public Customer getCustomer() {
        return customer;
    }
    public String getDate() {
        return date;
    }
    public double getSubtotal() {
        return quotation.getSubtotal();
    }
    public double getShippingCost() {
        return quotation.getShippingCost();
    }

}
