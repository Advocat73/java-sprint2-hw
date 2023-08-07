import java.util.ArrayList;

public class ReportEngine {

    MonthTotalPerYear monthTotalPerYear;
    String[] years;
    String[] months;
    ReportEngine() {
        monthTotalPerYear = new MonthTotalPerYear();
        years = monthTotalPerYear.getListYears();
        months = monthTotalPerYear.getListMonths();
    }

    int sverkaMonthYearReports(ArrayList<MonthlyReport> monthlyReports, ArrayList<YearlyReport> yearlyReports) {
        for (int cntYear = 0; cntYear < years.length; cntYear++) {
            for (int cntMonth = 0; cntMonth < monthlyReports.size(); cntMonth++) {
                int incomeInMonthReport = monthlyReports.get(cntMonth).getSumIncomeForMonth();
                int incomeInYearReport = yearlyReports.get(cntYear).getIncomePerMonth(cntMonth + 1);
                int expenseInMonthReport = monthlyReports.get(cntMonth).getSumExpenseForMonth();
                int expenseInYearReport = yearlyReports.get(cntYear).getExpensePerMonth(cntMonth + 1);
                if (incomeInMonthReport != incomeInYearReport || expenseInMonthReport != expenseInYearReport)
                    return cntMonth + 1;
            }
        }
        return 0;
    }
}