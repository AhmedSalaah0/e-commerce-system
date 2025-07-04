package ServiceContracts.Helpers;

public class InvoiceNumberGenerator {
    private static int invoiceCounter = 0;

    public static String generateInvoiceNumber() {
        invoiceCounter++;
        return "INV-" + String.format("%05d", invoiceCounter);
    }
}