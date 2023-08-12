import java.util.ArrayList;

public class ReportEngine {

    Transaction transaction;
    ArrayList<MonthlyReport> monthlyReports = new ArrayList<>(); // Здесь хранятся данные о месяцах. Один элемент списка - один месяц
    ArrayList<YearlyReport> yearlyReports = new ArrayList<>(); // Здесь хранятся данные о годах. Один элемент списка - один год
    ReportEngine() {
        transaction = new Transaction();
        monthlyReports = new ArrayList<>(); // Здесь хранятся данные о месяцах. Один элемент списка - один месяц
        yearlyReports = new ArrayList<>(); // Здесь хранятся данные о годах. Один элемент списка - один год
    }
    boolean addMonthDataFromFile (String fileName) {
        MonthlyReport monthlyReport = new MonthlyReport(); // Создаем класс для определенного месяца
        if (transaction.addMonthDataFromFile (fileName, monthlyReport)) {
            monthlyReports.add(monthlyReport);
            return true;
        }
        else return false;
    }
    boolean addYearDataFromFile (String fileName) {
        YearlyReport yearlyReport = new YearlyReport();
        if (transaction.addYearDataFromFile (fileName, yearlyReport)) {
            yearlyReports.add (yearlyReport);
            return true;
        }
        else return false;
    }
    void monthsDataClear () {
        monthlyReports.clear(); // Очищаем список, чтобы информация была актуальной и не дублировалась
    }
    void yearDataClear () {
        yearlyReports.clear(); // Очищаем список, чтобы информация была актуальной и не дублировалась
    }
    int monthsDataSize () {
        return monthlyReports.size();
    }
    int yearsDataSize () {
        return yearlyReports.size();
    }
    int compareMonthYearReports() {
        for (int cntYear = 0; cntYear < yearlyReports.size(); cntYear++) {
            for (int cntMonth = 0; cntMonth < monthlyReports.size(); cntMonth++) {
                int incomeInMonthReport = monthlyReports.get(cntMonth).getSumIncome(); // Суммарный доход за конкретный месяц
                int incomeInYearReport = yearlyReports.get(cntYear).getIncomePerMonth(cntMonth + 1); // Доход за конкретный месяц из годового отчета
                int expenseInMonthReport = monthlyReports.get(cntMonth).getSumExpense(); // Суммарный расход за конкретный месяц
                int expenseInYearReport = yearlyReports.get(cntYear).getExpensePerMonth(cntMonth + 1); // Расход за конкретный месяц из годового отчета
                if (incomeInMonthReport != incomeInYearReport || expenseInMonthReport != expenseInYearReport)
                    return cntMonth + 1;
            }
        }
        return 0;
    }
    int getBestIncomeForMonth (int iMonth) {
        return monthlyReports.get(iMonth).getBestIncome();
    }
    String getBestIncomeNameForMonth (int iMonth) {
        return monthlyReports.get(iMonth).getBestIncomeName();
    }
    int getBiggestExpenseForMonth (int iMonth) {
        return monthlyReports.get(iMonth).getBiggestExpense();
    }
    String getBiggestExpenseNameForMonth (int iMonth) {
        return monthlyReports.get(iMonth).getBiggestExpenseName();
    }
    int getCommonIncomePerYear(int year) {
        return yearlyReports.get(year).getCommonIncome();
    }
    int getCommonExpensePerYear(int year) {
        return yearlyReports.get(year).getCommonExpense();
    }
    int getIncomePerMonthFromYearReport (int iMonth, int year) {
        return yearlyReports.get(year).getIncomePerMonth(iMonth);
    }
    int getSumIncomeForMonth (int iMonth) {
        return monthlyReports.get(iMonth).getSumIncome();
    }
    int getSumExpenseForMonth (int iMonth) {
        return monthlyReports.get(iMonth).getSumExpense();
    }
}