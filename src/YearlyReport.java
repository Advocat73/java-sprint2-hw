import java.util.ArrayList;

public class YearlyReport {
    // Все данные года лежат в определенных массивах, т.к. операций в течении года много
    private ArrayList<Integer> monthIndexInYearReport;
    private ArrayList<Integer> monthResultInYearReport;
    private ArrayList<Boolean> isExpenseInYearReport;
    private int countRecords;

    YearlyReport () {
        monthIndexInYearReport = new ArrayList<>();
        monthResultInYearReport = new ArrayList<>();
        isExpenseInYearReport = new ArrayList<>();
        countRecords = 0;
    }
    void addYearData (int monthIndex, int monthResult, boolean isExpense) {
            monthIndexInYearReport.add(monthIndex);
            monthResultInYearReport.add(monthResult);
            isExpenseInYearReport.add(isExpense);
            countRecords++;
    }

    int getCommonIncome () {
        int commonIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpenseInYearReport.get(i)) {
                commonIncome += monthResultInYearReport.get(i);
            }
        }
        return commonIncome;
    }
    int getCommonExpense () {
        int commonExpense = 0;
        for (int i = 0; i < countRecords; i++) {
            if (isExpenseInYearReport.get(i)) {
                commonExpense += monthIndexInYearReport.get(i);
            }
        }
        return commonExpense;
    }
    int getIncomePerMonth (int monthIndex) {
        for (int i = 0; i < countRecords; i++) {
            if (monthIndexInYearReport.get(i) == monthIndex && !isExpenseInYearReport.get(i)) {
                return monthResultInYearReport.get(i);
            }
        }
        return 0;
    }
    int getExpensePerMonth (int monthIndex) {
        for (int i = 0; i < countRecords; i++) {
            if (monthIndexInYearReport.get(i) == monthIndex && isExpenseInYearReport.get(i)) {
                return monthResultInYearReport.get(i);
            }
        }
        return 0;
    }
}
