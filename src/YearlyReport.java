import java.util.ArrayList;

public class YearlyReport {
    double getCommonIncomePerYear (Transaction transaction) {
        double commonIncome = 0;
        for (int i = 0; i < transaction.countRecords; i++) {
            if (!transaction.isExpenseInYearReport[i]) {
                commonIncome += transaction.monthResultInYearReport[i];
            }
        }
        return commonIncome;
    }
    double getCommonExpensePerYear (Transaction transaction) {
        double commonExpense = 0;
        for (int i = 0; i < transaction.countRecords; i++) {
            if (transaction.isExpenseInYearReport[i]) {
                commonExpense += transaction.monthResultInYearReport[i];
            }
        }
        return commonExpense;
    }
    int getIncomePerMonth (Transaction transaction, int monthIndex) {
        for (int i = 0; i < transaction.countRecords; i++) {
            if (transaction.monthIndexInYearReport[i] == monthIndex && !transaction.isExpenseInYearReport[i]) {
                return transaction.monthResultInYearReport[i];
            }
        }
        return 0;
    }
    int getExpensePerMonth (Transaction transaction, int monthIndex) {
        for (int i = 0; i < transaction.countRecords; i++) {
            if (transaction.monthIndexInYearReport[i] == monthIndex && transaction.isExpenseInYearReport[i]) {
                return transaction.monthResultInYearReport[i];
            }
        }
        return 0;
    }
}
