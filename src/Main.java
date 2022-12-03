import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();
        YearlyReport yReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker(monthlyReport, yReport);

        while (userInput != 123) {


            if (userInput == 1) {
                {

                    monthlyReport.createReport();

                }
                System.out.println("Месячные отчеты загружены!");
            } else if (userInput == 2) {
                yReport.createYearReport(2021);

            } else if (userInput == 3) {

                checker.check();

            } else if (userInput == 4) {
                if (!monthlyReport.dataMonth.isEmpty()) {
                    monthlyReport.doStatistics();
                } else {
                    System.out.println("Месячные отчеты не загружены!");
                }
            } else if (userInput == 5) {
                if (!yReport.yearStatistic.isEmpty()) {
                    yReport.monthProfit();
                    System.out.println("Средний расход за все месяцы в году: " + yReport.averageExpense());
                    System.out.println("Средний доход за все месяцы в году: " + yReport.averageProfit());
                } else {
                    System.out.println("Годовой отчет не загружен!");
                }
            } else {
                System.out.println("Извините, такой команды нет.");
            }
            printMenu();
            userInput = scanner.nextInt();
        }

    }


    private static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("Наберите '123' для завершения программы.");
    }
}




