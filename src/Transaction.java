import java.util.ArrayList;

public class Transaction {
    public boolean addMonthDataFromFile (String fileName, MonthlyReport monthlyReport) {
        FileReader fileReader = new FileReader();
        ArrayList<String> list = fileReader.readFileContents(fileName);
        if (list != null) {
            // Создаем соответствующие массивы известного размера
            monthlyReport.name = new String[list.size()];
            monthlyReport.isExpense = new boolean[list.size()];
            monthlyReport.quantity = new int[list.size()];
            monthlyReport.unitPrice = new int[list.size()];
            // Заполняем массивы
            for (int i = 1; i < list.size(); i++) {
                String line = list.get(i);
                String[] lineContents = line.split(",");
                monthlyReport.name[i-1] = lineContents[0];
                monthlyReport.isExpense[i-1] = Boolean.parseBoolean(lineContents[1]);
                monthlyReport.quantity[i-1] = Integer.parseInt(lineContents[2]);
                monthlyReport.unitPrice[i-1] = Integer.parseInt(lineContents[3]);
                monthlyReport.countRecords++;
            }
            return true;
        } else
            return false;
    }
    public boolean addYearDataFromFile (String fileName, YearlyReport yearlyReport) {
        FileReader fileReader = new FileReader();
        ArrayList<String> list = fileReader.readFileContents(fileName);
        if (list != null) {
            // Создаем соответствующие массивы известного размера
            yearlyReport.monthIndexInYearReport = new int[list.size()];
            yearlyReport.monthResultInYearReport = new int[list.size()];
            yearlyReport.isExpenseInYearReport = new boolean[list.size()];
            // Заполняем массивы
            for (int i = 1; i < list.size(); i++) {
                String line = list.get(i);
                String[] lineContents = line.split(",");
                yearlyReport.monthIndexInYearReport[i-1] = Integer.parseInt(lineContents[0]);
                yearlyReport.monthResultInYearReport[i-1] = Integer.parseInt(lineContents[1]);
                yearlyReport.isExpenseInYearReport[i-1] = Boolean.parseBoolean(lineContents[2]);
                yearlyReport.countRecords++;
            }
            return true;
        } else
            return false;
    }
}
