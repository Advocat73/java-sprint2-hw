import java.util.ArrayList;
public class MonthlyReport {
    int getBestIncomeMonthByMonth (Transaction transaction) {
        int bestIncome = 0;
        for (int i = 0; i < transaction.countRecords; i++) {
            if (!transaction.isExpense[i] && (transaction.quantity[i]*transaction.unitPrice[i]) > bestIncome)
                bestIncome = (transaction.quantity[i]*transaction.unitPrice[i]);
        }
            return bestIncome;
    }
    String getBestIncomeNameMonthByMonth (Transaction transaction) {
        String bestIncomeName = "";
        int bestIncome = 0;
        for (int i = 0; i < transaction.countRecords; i++) {
            if (!transaction.isExpense[i] && (transaction.quantity[i]*transaction.unitPrice[i]) > bestIncome) {
                bestIncome = (transaction.quantity[i]*transaction.unitPrice[i]);
                bestIncomeName = transaction.name[i];
            }
        }
        return bestIncomeName;
    }
    int getBiggestExpenseMonthByMonth(Transaction transaction) {
        int biggestExpense = 0;

        for (int i = 0; i < transaction.countRecords; i++) {
            if (transaction.isExpense[i] && (transaction.quantity[i]*transaction.unitPrice[i]) > biggestExpense) {
                biggestExpense = (transaction.quantity[i]*transaction.unitPrice[i]);
            }
        }
        return biggestExpense;
    }
    String getBiggestExpenseNameMonthByMonth(Transaction transaction) {
        int biggestExpense = 0;
        String biggestExpenseName = "";
        for (int i = 0; i < transaction.countRecords; i++) {
            if (transaction.isExpense[i] && (transaction.quantity[i]*transaction.unitPrice[i]) > biggestExpense) {
                biggestExpense = (transaction.quantity[i]*transaction.unitPrice[i]);
                biggestExpenseName = transaction.name[i];
            }
        }
        return biggestExpenseName;
    }
    int getSumIncomeForMonth (Transaction transaction) {
        int sumIncome = 0;
        for (int i = 0; i < transaction.countRecords; i++) {
            if (!transaction.isExpense[i]) {
                sumIncome += transaction.quantity[i]*transaction.unitPrice[i];
            }
        }
        return sumIncome;
    }
    int getSumExpenseForMonth (Transaction transaction) {
        int sumExpense = 0;
        for (int i = 0; i < transaction.countRecords; i++) {
            if (transaction.isExpense[i]) {
                sumExpense += transaction.quantity[i]*transaction.unitPrice[i];
            }
        }
        return sumExpense;
    }
}
