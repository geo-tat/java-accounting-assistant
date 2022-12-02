public class Sale {
    String item;
    boolean isExpense;
    int quantity;
    int sum;

    String monthNumber;

    public Sale(String item, boolean isExpense, int quantity, int sum, String convert) {
        this.item = item;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sum = sum;
        this.monthNumber = convert;
    }
}
