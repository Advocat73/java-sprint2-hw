import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
    // Все данные месяца лежат в определенных массивах, т.к. операций в течении месяца много
    String[] name;
    boolean[] isExpense;
    int[] quantity;
    int[] unitPrice;
    int[] monthIndexInYearReport;
    int[] monthResultInYearReport;
    boolean[] isExpenseInYearReport;
    int countRecords = 0; // Мы должны знать количество записей в массивах

    public boolean addMonthDataFromFile (String fileName) {
        FileReader fileReader = new FileReader();
        ArrayList<String> list = fileReader.readFileContents(fileName);
        if (list != null) {
            // Создаем соответствующие массивы известного размера
            name = new String[list.size()];
            isExpense = new boolean[list.size()];
            quantity = new int[list.size()];
            unitPrice = new int[list.size()];
            // Заполняем массивы
            for (int i = 1; i < list.size(); i++) {
                String line = list.get(i);
                String[] lineContents = line.split(",");
                name[i-1] = lineContents[0];
                isExpense[i-1] = Boolean.parseBoolean(lineContents[1]);
                quantity[i-1] = Integer.parseInt(lineContents[2]);
                unitPrice[i-1] = Integer.parseInt(lineContents[3]);
                countRecords++;
            }
            return true;
        } else
            return false;
    }
    public boolean addYearDataFromFile (String fileName) {
        FileReader fileReader = new FileReader();
        ArrayList<String> list = fileReader.readFileContents(fileName);
        if (list != null) {
            // Создаем соответствующие массивы известного размера
            monthIndexInYearReport = new int[list.size()];
            monthResultInYearReport = new int[list.size()];
            isExpenseInYearReport = new boolean[list.size()];
            // Заполняем массивы
            for (int i = 1; i < list.size(); i++) {
                String line = list.get(i);
                String[] lineContents = line.split(",");
                monthIndexInYearReport[i-1] = Integer.parseInt(lineContents[0]);
                monthResultInYearReport[i-1] = Integer.parseInt(lineContents[1]);
                isExpenseInYearReport[i-1] = Boolean.parseBoolean(lineContents[2]);
                countRecords++;
            }
            return true;
        } else
            return false;
    }
}
