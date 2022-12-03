import java.util.HashMap;

public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;
    HashMap<String, Integer> yearExp = new HashMap<>();
    HashMap<String, Integer> yearProfit = new HashMap<>();


    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public void check() {
        if (!monthlyReport.dataMonth.isEmpty() && !yearlyReport.yearStatistic.isEmpty()) {
            for (YearSale yearSale : YearlyReport.yearSales) {
                if (yearSale.isExpense) {
                    yearExp.put(monthlyReport.convert(yearSale.month), yearSale.amount);
                } else if (!yearSale.isExpense) {
                    yearProfit.put(monthlyReport.convert(yearSale.month), yearSale.amount);
                }
            }
            for (int i = 1; i <= monthlyReport.dataMonth.size(); i++) {
                if (yearExp.containsKey(monthlyReport.convert(i)) && yearProfit.containsKey(monthlyReport.convert(i))) {
                    if (yearExp.get(monthlyReport.convert(i)) == monthlyReport.sumOfExpenses(i) && yearProfit.get(monthlyReport.convert(i)) == monthlyReport.sumOfProfits(i)) {
                        System.out.println("Отчеты за " + monthlyReport.convert(i) + " месяц сошлись.");

                    } else {
                        System.out.println("Отчеты за " + monthlyReport.convert(i) + " не сходятся");
                    }
                } else {
                    System.out.println("Данные за " + monthlyReport.convert(i) + " присутствуют в неполном объеме");
                }
            }
        } else {
            System.out.println("Отчеты не загружены!");
        }
    }
}