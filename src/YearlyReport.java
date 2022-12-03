import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    Year year;
    HashMap<Integer, ArrayList<YearSale>> yearStatistic = new HashMap<>();
    static ArrayList<YearSale> yearSales = new ArrayList<>();

    public void createYearReport(int yearNumber) {
        String content = readFileContentsOrNull("resources/y." + yearNumber + ".csv");
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] part = line.split(",");
            int month = Integer.parseInt(part[0]);
            int amount = Integer.parseInt(part[1]);
            boolean isExpense = Boolean.parseBoolean(part[2]);
            YearSale yearSale = new YearSale(month, amount, isExpense);
            yearSales.add(yearSale);
        }
        yearStatistic.put(yearNumber, yearSales);
        System.out.printf("Отчет за" + yearNumber + " год создан!");
    }

    public void monthProfit() {
        HashMap<Integer, Integer> profit = new HashMap<>();
        HashMap<Integer, Integer> outcome = new HashMap<>();

        for (YearSale yearSale : yearSales) {
            if (yearSale.isExpense) {
                outcome.put(yearSale.month, yearSale.amount);
            } else {
                profit.put(yearSale.month, yearSale.amount);
            }
        }
        System.out.println("Год: " + year);
        for (int i = 1; i <= profit.size(); i++) {
            System.out.println("Прибыль за " + i + "-й месяц: " + (profit.get(i) - outcome.get(i)));
        }

    }


    public int averageProfit() {
        int sum = 0;


        for (YearSale yearSale : yearSales) {
            if (!yearSale.isExpense) {
                sum += yearSale.amount;

            }
        }
        return (sum / 12);
    }

    public int averageExpense() {
        int sum = 0;

        for (YearSale yearSale : yearSales) {
            if (yearSale.isExpense) {
                sum += yearSale.amount;

            }
        }
        return (sum / 12);
    }

    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
