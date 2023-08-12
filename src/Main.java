import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReportEngine reportEngine = new ReportEngine();
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        String[] months = monthTotalPerYear.getListMonths(); // С помощью этой структуры месяцы меняем только в одном месте - в классе MonthTotalPerYear
        String[] years = monthTotalPerYear.getListYears();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                reportEngine.monthsDataClear (); // Обнуляем, чтобы заново записывать
                for (int i = 1; i <= months.length; i++) {
                    String fileName = "";
                    if (i<10)
                        fileName = "m.20210" + i + ".csv";
                    else
                        fileName = "m.2021" + i + ".csv";
                    if (reportEngine.addMonthDataFromFile(fileName))
                        System.out.println("Месяный отчет из файла " + fileName + " считан");
                    else {
                        System.out.println("Файл " + fileName + " не найден или он пустой");
                        reportEngine.monthsDataClear (); // Обнуляем, т.к. что-то пошло не так
                        break;
                    }
                }
            } else if (userInput == 2) {
                reportEngine.yearDataClear ();
                for (int i = 21; i < years.length; i++) {
                    String fileName = "y.20" + i + ".csv";
                    if (reportEngine.addYearDataFromFile(fileName))
                        System.out.println("Годовой отчет из файла " + fileName + " считан");
                    else {
                        System.out.println("Файл " + fileName + " не найден");
                        reportEngine.yearDataClear();
                        break;
                    }
                }
            } else if (userInput == 3) {
                if (reportEngine.monthsDataSize() == 0 || reportEngine.yearsDataSize() == 0) // Не считали месячные или годовые отчеты
                    System.out.println("Надо сначала считать месячные и годовые отчеты");
                else {
                    printResultMonthByMonth (reportEngine, months);
                    int check = reportEngine.compareMonthYearReports ();
                    if (check != 0)
                        System.out.println("Несоответствие данных выявлено в " + months[check - 1]);
                    else
                        System.out.println("Сверка прошла успешно");
                }
            } else if (userInput == 4) {
                if (reportEngine.monthsDataSize() == 0) // Не считали месячные отчеты
                    System.out.println("Надо сначала считать месячные отчеты");
                else
                    printInfoMonthByMonth (reportEngine, months);
            } else if (userInput == 5) {
                if (reportEngine.yearsDataSize() == 0) // Не считали годовые отчеты
                    System.out.println("Надо сначала считать годовые отчеты");
                else
                    printInfoAboutYearReports (reportEngine, months, years);
            } else if (userInput == 0) {
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
        System.out.println("0 - Выйти из приложения");
    }
    static void printResultMonthByMonth (ReportEngine reportEngine, String[] months) {
        for (int cntMonth = 0; cntMonth < reportEngine.monthsDataSize(); cntMonth++) {
            System.out.println("Данные за " + months[cntMonth] + ":");
            System.out.println("Суммарный доход: " + reportEngine.getSumIncomeForMonth(cntMonth));
            System.out.println("Общая сумма расходов: " + reportEngine.getSumExpenseForMonth(cntMonth));
        }
    }
    static void printInfoMonthByMonth (ReportEngine reportEngine, String[] months) {
        for (int cntMonth = 0; cntMonth < reportEngine.monthsDataSize(); cntMonth++) {
            int bestIncome = reportEngine.getBestIncomeForMonth(cntMonth); // Лучшый доход за конкретный месяц
            String bestIncomeName = reportEngine.getBestIncomeNameForMonth(cntMonth); // Самая доходная позиция за конкретный месяц
            int biggestExpense = reportEngine.getBiggestExpenseForMonth(cntMonth); // Самые большие затраты за конкретный месяц
            String biggestExpenseName = reportEngine.getBiggestExpenseNameForMonth(cntMonth); // Самая затратная позиция за конкретный месяц
            System.out.println("Данные за " + months[cntMonth] + ":");
            System.out.println("Самый прибыльный товар: " + bestIncomeName + ". Продан за " + bestIncome + ".");
            System.out.println("Самая большая трата: " + biggestExpenseName + ". Потрачено " + biggestExpense + ".");
        }
    }
    static void printInfoAboutYearReports (ReportEngine reportEngine, String[] months, String[] years) {
        for (int cntYear = 0; cntYear < reportEngine.yearsDataSize(); cntYear++) {
            double commonIncome = (double) reportEngine.getCommonIncomePerYear(cntYear); // Суммарные доходы по годовому отчету
            double commonExpense = (double) reportEngine.getCommonExpensePerYear(cntYear); // Суммарные траты по годовому отчету
            System.out.println("Данные за " + months.length + " месяца " + years[cntYear] + " года:");
            for (int cntMonth = 0; cntMonth < months.length; cntMonth++) {
                System.out.println("Прибыль за " + months[cntMonth] + "месяца "+ years[cntYear] + " года составляет " + reportEngine.getIncomePerMonthFromYearReport((cntMonth+1), cntYear));
            }
            System.out.println("Cредний расход за все имеющиеся операции в " + years[cntYear] + " году: " + (commonExpense/months.length));
            System.out.println("Cредний доход  за все имеющиеся операции в " + years[cntYear] + " году: " + (commonIncome/months.length));
        }
    }
}