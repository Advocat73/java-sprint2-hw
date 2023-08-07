import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReportEngine reportEngine = new ReportEngine();
        Transaction transaction = new Transaction();
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>(); // Здесь хранятся данные о месяцах. Один элемент списка - один месяц
        ArrayList<YearlyReport> yearlyReports = new ArrayList<>(); // Здесь хранятся данные о годах. Один элемент списка - один год
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        String[] months = monthTotalPerYear.getListMonths(); // С помощью этой структуры месяцы меняем только в одном месте - в классе MonthTotalPerYear
        String[] years = monthTotalPerYear.getListYears();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReports.clear(); // Очищаем список, чтобы информация была актуальной и не дублировалась
                for (int i = 1; i <= months.length; i++) {
                    MonthlyReport monthlyReport = new MonthlyReport(); // Создаем класс для определенного месяца
                    String fileName = "m.20210" + i + ".csv";
                    if (transaction.addMonthDataFromFile(fileName, monthlyReport)) { // Считываем файл, если да, то данные кладем в monthlyReport
                        System.out.println("Месяный отчет из файла " + fileName + " считан");
                        monthlyReports.add(monthlyReport); // Добавляем класс с данными за месяц в список
                    }
                    else
                        System.out.println("файл " + fileName + " не найден");
                }
            } else if (userInput == 2) {
                yearlyReports.clear(); // Очищаем список, чтобы информация была актуальной и не дублировалась
                for (int i = 1; i <= months.length; i++) {
                    YearlyReport yearlyReport = new YearlyReport(); // Создаем класс для определенного года
                    String fileName = "y.202" + i + ".csv";
                    if (transaction.addYearDataFromFile(fileName, yearlyReport)) { // Считываем файл, если да, то данные кладе в yearlyReport
                        System.out.println("Годовой отчет из файла " + fileName + " считан");
                        yearlyReports.add(yearlyReport); // Добавляем класс с данными за год в список
                    }
                    else
                        System.out.println("файл " + fileName + " не найден");
                }
            } else if (userInput == 3) {
                if (monthlyReports.size() == 0 || yearlyReports.size() == 0)
                    System.out.println("Надо сначала считать месячные и годовые отчеты");
                else {
                    // printResultMonthByMonth (monthlyReports, months);
                    int check = reportEngine.sverkaMonthYearReports (monthlyReports, yearlyReports);
                    if (check != 0)
                        System.out.println("Несоответствие данных выявлено в " + months[check - 1]);
                    else
                        System.out.println("Сверка прошла успешно");
                }
            } else if (userInput == 4) {
                if (monthlyReports.size() == 0)
                    System.out.println("Надо сначала считать месячные отчеты");
                else
                    printInfoAboutMonthReports (monthlyReports, months);
            } else if (userInput == 5) {
                if (yearlyReports.size() == 0)
                    System.out.println("Надо сначала считать годовые отчеты");
                else
                    printInfoAboutYearReports (yearlyReports, months, years);
            } else if (userInput == 6) {
                System.out.println("Пока! Пока!");
                scanner.close();
                break;
            } else {
                System.out.println("Такой команды нет");
            }
        }
    }

    static void printMenu() {
        // Вывод доступных команд
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выйти из приложения");
    }

    static void printResultMonthByMonth (ArrayList<MonthlyReport> monthlyReports, String[] months) {
        for (int cntMonth = 0; cntMonth < monthlyReports.size(); cntMonth++) {
            System.out.println("Данные за " + months[cntMonth] + ":");
            System.out.println("Суммарный доход: " + monthlyReports.get(cntMonth).getSumIncomeForMonth());
            System.out.println("Общая сумма расходов: " + monthlyReports.get(cntMonth).getSumExpenseForMonth());
        }
    }

    static void printInfoAboutMonthReports (ArrayList<MonthlyReport> monthlyReports, String[] months) {
        for (int cntMonth = 0; cntMonth < monthlyReports.size(); cntMonth++) {
            int bestIncome = monthlyReports.get(cntMonth).getBestIncomeForMonth();
            String bestIncomeName = monthlyReports.get(cntMonth).getBestIncomeNameForMonth();
            int biggestExpense = monthlyReports.get(cntMonth).getBiggestExpenseForMonth();
            String biggestExpenseName = monthlyReports.get(cntMonth).getBiggestExpenseNameForMonth();
            System.out.println("Данные за " + months[cntMonth] + ":");
            System.out.println("Самый прибыльный товар: " + bestIncomeName + ". Продан за " + bestIncome + ".");
            System.out.println("Самая большая трата: " + biggestExpenseName + ". Потрачено " + biggestExpense + ".");
        }
    }

    static void printInfoAboutYearReports (ArrayList<YearlyReport> yearlyReports, String[] months, String[] years) {
        for (int cntYear = 0; cntYear < yearlyReports.size(); cntYear++) {
            double commonIncome = yearlyReports.get(cntYear).getCommonIncomePerYear();
            double commonExpense = yearlyReports.get(cntYear).getCommonExpensePerYear();
            System.out.println("Данные за " + months.length + " месяца " + years[cntYear] + " года:");
            for (int cntMonth = 0; cntMonth < months.length; cntMonth++) {
                System.out.println("Прибыль за " + months[cntMonth] + " составляет " + yearlyReports.get(cntYear).getIncomePerMonth(cntMonth+1));
            }
            System.out.println("Cредний расход за все имеющиеся операции в " + years[cntYear] + " году: " + (commonExpense/months.length));
            System.out.println("Cредний доход  за все имеющиеся операции в " + years[cntYear] + " году: " + (commonIncome/months.length));
        }
    }
}