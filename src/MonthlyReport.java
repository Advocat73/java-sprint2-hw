public class MonthlyReport {
    // Все данные месяца лежат в определенных массивах, т.к. операций в течении месяца много
    String[] name;
    boolean[] isExpense;
    int[] quantity;
    int[] unitPrice;
    int countRecords = 0; // Мы должны знать количество записей в массивах

    int getBestIncomeForMonth () {
        int bestIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpense[i] && (quantity[i]*unitPrice[i]) > bestIncome)
                bestIncome = (quantity[i]*unitPrice[i]);
        }
            return bestIncome;
    }
    String getBestIncomeNameForMonth () {
        String bestIncomeName = "";
        int bestIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpense[i] && (quantity[i]*unitPrice[i]) > bestIncome) {
                bestIncome = (quantity[i]*unitPrice[i]);
                bestIncomeName = name[i];
            }
        }
        return bestIncomeName;
    }
    int getBiggestExpenseForMonth() {
        int biggestExpense = 0;

        for (int i = 0; i < countRecords; i++) {
            if (isExpense[i] && (quantity[i]*unitPrice[i]) > biggestExpense) {
                biggestExpense = (quantity[i]*unitPrice[i]);
            }
        }
        return biggestExpense;
    }
    String getBiggestExpenseNameForMonth() {
        int biggestExpense = 0;
        String biggestExpenseName = "";
        for (int i = 0; i < countRecords; i++) {
            if (isExpense[i] && (quantity[i]*unitPrice[i]) > biggestExpense) {
                biggestExpense = (quantity[i]*unitPrice[i]);
                biggestExpenseName = name[i];
            }
        }
        return biggestExpenseName;
    }
    int getSumIncomeForMonth () {
        int sumIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpense[i]) {
                sumIncome += quantity[i]*unitPrice[i];
            }
        }
        return sumIncome;
    }
    int getSumExpenseForMonth () {
        int sumExpense = 0;
        for (int i = 0; i < countRecords; i++) {
            if (isExpense[i]) {
                sumExpense += quantity[i]*unitPrice[i];
            }
        }
        return sumExpense;
    }
}
