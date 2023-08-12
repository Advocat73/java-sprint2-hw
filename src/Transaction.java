import java.util.ArrayList;
public class Transaction {
    public boolean addMonthDataFromFile (String fileName, MonthlyReport monthlyReport) {
        FileReader fileReader = new FileReader();
        ArrayList<String> list = fileReader.readFileContents(fileName);
        if (!list.isEmpty()) {
            // Построчно даем данные
            for (int i = 1; i < list.size(); i++) {
                String line = list.get(i);
                String[] lineContents = line.split(",");
                monthlyReport.addMonthData( lineContents[0],
                                            Boolean.parseBoolean(lineContents[1]),
                                            Integer.parseInt(lineContents[2]),
                                            Integer.parseInt(lineContents[3]));
            }
            return true;
        } else
            return false;
    }
    public boolean addYearDataFromFile (String fileName, YearlyReport yearlyReport) {
        FileReader fileReader = new FileReader();
        ArrayList<String> list = fileReader.readFileContents(fileName);
        if (!list.isEmpty()) {
            // Построчно даем данные
            for (int i = 1; i < list.size(); i++) {
                String line = list.get(i);
                String[] lineContents = line.split(",");
                yearlyReport.addYearData (  Integer.parseInt(lineContents[0]),
                                            Integer.parseInt(lineContents[1]),
                                            Boolean.parseBoolean(lineContents[2]));
            }
            return true;
        } else
            return false;
    }
}
