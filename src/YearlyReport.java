public class YearlyReport {
    // Все данные года лежат в определенных массивах, т.к. операций в течении года много
    int[] monthIndexInYearReport;
    int[] monthResultInYearReport;
    boolean[] isExpenseInYearReport;
    int countRecords = 0; // Мы должны знать количество записей в массивах
    double getCommonIncomePerYear () {
        double commonIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpenseInYearReport[i]) {
                commonIncome += monthResultInYearReport[i];
            }
        }
        return commonIncome;
    }
    double getCommonExpensePerYear () {
        double commonExpense = 0;
        for (int i = 0; i < countRecords; i++) {
            if (isExpenseInYearReport[i]) {
                commonExpense += monthResultInYearReport[i];
            }
        }
        return commonExpense;
    }
    int getIncomePerMonth (int monthIndex) {
        for (int i = 0; i < countRecords; i++) {
            if (monthIndexInYearReport[i] == monthIndex && !isExpenseInYearReport[i]) {
                return monthResultInYearReport[i];
            }
        }
        return 0;
    }
    int getExpensePerMonth (int monthIndex) {
        for (int i = 0; i < countRecords; i++) {
            if (monthIndexInYearReport[i] == monthIndex && isExpenseInYearReport[i]) {
                return monthResultInYearReport[i];
            }
        }
        return 0;
    }
}
