import java.util.ArrayList;

public class MonthlyReport {
    private ArrayList<String> name;
    private ArrayList<Boolean> isExpense;
    private ArrayList<Integer> quantity;
    private ArrayList<Integer> unitPrice;
    private int countRecords;

    MonthlyReport () {
        name = new ArrayList<>();
        isExpense = new ArrayList<>();
        quantity = new ArrayList<>();
        unitPrice = new ArrayList<>();
        countRecords = 0;
    }
    void addMonthData (String name, boolean isExpense, int quantity, int unitPrice) {
            this.name.add(name);
            this.isExpense.add(isExpense);
            this.quantity.add(quantity);
            this.unitPrice.add(unitPrice);
            countRecords++;
    }
    int getBestIncome () {
        int bestIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpense.get(i) && (quantity.get(i)*unitPrice.get(i) > bestIncome))
                bestIncome = quantity.get(i)*unitPrice.get(i);
        }
        return bestIncome;
    }
    String getBestIncomeName () {
        String bestIncomeName = "";
        int bestIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpense.get(i) && (quantity.get(i)*unitPrice.get(i)) > bestIncome) {
                bestIncome = (quantity.get(i)*unitPrice.get(i));
                bestIncomeName = name.get(i);
            }
        }
        return bestIncomeName;
    }
    int getBiggestExpense() {
        int biggestExpense = 0;

        for (int i = 0; i < countRecords; i++) {
            if (isExpense.get(i) && (quantity.get(i)*unitPrice.get(i)) > biggestExpense) {
                biggestExpense = (quantity.get(i)*unitPrice.get(i));
            }
        }
        return biggestExpense;
    }
    String getBiggestExpenseName() {
        int biggestExpense = 0;
        String biggestExpenseName = "";
        for (int i = 0; i < countRecords; i++) {
            if (isExpense.get(i) && (quantity.get(i)*unitPrice.get(i)) > biggestExpense) {
                biggestExpense = (quantity.get(i)*unitPrice.get(i));
                biggestExpenseName = name.get(i);
            }
        }
        return biggestExpenseName;
    }
    int getSumIncome () {
        int sumIncome = 0;
        for (int i = 0; i < countRecords; i++) {
            if (!isExpense.get(i)) {
                sumIncome += quantity.get(i)*unitPrice.get(i);
            }
        }
        return sumIncome;
    }
    int getSumExpense () {
        int sumExpense = 0;
        for (int i = 0; i < countRecords; i++) {
            if (isExpense.get(i)) {
                sumExpense += quantity.get(i)*unitPrice.get(i);
            }
        }
        return sumExpense;
    }
}
