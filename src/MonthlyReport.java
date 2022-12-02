import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {


    HashMap<Integer, String> data = new HashMap<>();
    HashMap<String, ArrayList<Sale>> dataMonth = new HashMap<>();

    void createReport() {
        for (int i = 1; i <= 3; i++) {
            monthlyReport(i);
            System.out.println("Отчет за " + convert(i) + " месяц создан.");
        }

    }

    void monthlyReport(int monthNumber) {

        String content = readFileContentsOrNullMonth("resources/m.20210" + monthNumber + ".csv");
        String[] lines = content.split("\n");
        ArrayList<Sale> sales = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] part = line.split(",");
            String item = part[0];

            boolean isExpense = Boolean.parseBoolean(part[1]);
            int quantity = Integer.parseInt(part[2]);
            int sum = Integer.parseInt(part[3]);

            Sale sale = new Sale(item, isExpense, quantity, sum, convert(monthNumber));


            sales.add(sale);
        }
        dataMonth.put(convert(monthNumber), sales);

    }

    public void doStatistics() {

        for (int i = 1; i <= dataMonth.size(); i++) {
            System.out.println("Статистика за " + convert(i) + " месяц:");
            doProfit(i);
            doExpense(i);
        }

    }

    public void doExpense(int month) {
        ArrayList<Sale> expenses;
        int price = 0;
        String item = null;
        String topItem = null;
        expenses = dataMonth.get(convert(month));
        int maxSum = 0;
        for (Sale sale : expenses) {
            if (sale.isExpense == true) {
                price = sale.quantity * sale.sum;
                item = sale.item;

                if (price > maxSum) {
                    maxSum = price;
                    topItem = sale.item;
                }
            }

        }
        System.out.println("Самая большая трата: " + topItem);
        System.out.println("Сумма: " + maxSum);
    }

    public void doProfit(int month) {
        ArrayList<Sale> value = new ArrayList<Sale>();
        int price = 0;
        String item = null;
        String topItem = null;
        value = dataMonth.get(convert(month));
        int maxSum = 0;
        for (Sale sale : value) {
            if (sale.isExpense == false) {
                price = sale.quantity * sale.sum;
                item = sale.item;

                if (price > maxSum) {
                    maxSum = price;
                    topItem = sale.item;
                }
            }

        }

        System.out.println("Самый прибыльный товар: " + topItem);
        System.out.println("Сумма прибыли: " + maxSum);
    }

    public String readFileContentsOrNullMonth(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public int maxExpense(int month) {
        ArrayList<Sale> expenses = new ArrayList<Sale>();
        int totalExp = 0;
        expenses = dataMonth.get(convert(month));
        for (Sale sale : expenses) {
            if (sale.isExpense == true) {
                totalExp = totalExp + sale.sum * sale.quantity;
            }
        }
        return totalExp;
    }

    public int maxSum(int month) {
        ArrayList<Sale> profit = new ArrayList<Sale>();
        int totalSum = 0;
        profit = dataMonth.get(convert(month));
        for (Sale sale : profit) {
            if (sale.isExpense == false) {
                totalSum = totalSum + sale.sum * sale.quantity;
            }
        }
        return totalSum;
    }

    String convert(int month) {
        data.put(1, "Январь");
        data.put(2, "Февраль");
        data.put(3, "Март");
        data.put(4, "Апрель");
        data.put(5, "Май");
        data.put(6, "Июнь");
        data.put(7, "Июль");
        data.put(8, "Август");
        data.put(9, "Сентябрь");
        data.put(10, "Октябрь");
        data.put(11, "Ноябрь");
        data.put(12, "Декабрь");
        return data.get(month);
    }
}

