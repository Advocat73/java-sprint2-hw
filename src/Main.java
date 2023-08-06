import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Transaction> monthTransactions = new ArrayList<>(); // Здесь хранятся данные о месяцах. Один элемент списка - один месяц
        ArrayList<Transaction> yearTransactions = new ArrayList<>(); // Здесь хранятся данные о годах. Один элемент списка - один год
        MonthlyReport monthlyReport = new MonthlyReport(); // В этом классе все методы, связанные с обработкой информации в месячных отчетах
        YearlyReport yearlyReport = new YearlyReport(); // В этом классе все методы, связанные с обработкой информации в годовых отчетах
        MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
        String[] months = monthTotalPerYear.getListMonths(); // С помощью этой структуры месяцы меняем только в одном месте - в классе MonthTotalPerYear
        String[] years = monthTotalPerYear.getListYears();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthTransactions.clear();
                for (int i = 1; i <= monthTotalPerYear.getListMonths().length; i++) {
                    Transaction transaction = new Transaction();
                    String fileName = "m.20210" + i + ".csv";
                    if (transaction.addMonthDataFromFile(fileName)) {
                        System.out.println("Месяный отчет из файла " + fileName + " считан");
                        // Добавили данные за очередной месяц в список
                        monthTransactions.add(transaction);
                    }
                    else
                        System.out.println("файл " + fileName + " не найден");
                }
            } else if (userInput == 2) {
                yearTransactions.clear();
                for (int i = 1; i <= monthTotalPerYear.getListYears().length; i++) {
                    Transaction transaction = new Transaction();
                    String fileName = "y.202" + i + ".csv";
                    if (transaction.addYearDataFromFile(fileName)) {
                        System.out.println("Годовой отчет из файла " + fileName + " считан");
                        // Добавили данные за очередной год в список
                        yearTransactions.add(transaction);
                    }
                    else
                        System.out.println("файл " + fileName + " не найден");
                }
            } else if (userInput == 3) {
                if (monthTransactions.size() == 0 || yearTransactions.size() == 0)
                    System.out.println("Надо сначала считать месячные и годовые отчеты");
                else
                    printSverkaResult (monthTransactions, yearTransactions, monthlyReport, yearlyReport, months, years);
            } else if (userInput == 4) {
                if (monthTransactions.size() == 0)
                    System.out.println("Надо сначала считать месячные отчеты");
                else
                    printInfoAboutMonthReports (monthTransactions, monthlyReport, months);
            } else if (userInput == 5) {
                if (yearTransactions.size() == 0)
                    System.out.println("Надо сначала считать годовые отчеты");
                else
                    printInfoAboutYearReports (yearTransactions, yearlyReport, months, years);
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

    static void printSverkaResult (ArrayList<Transaction> monthTransactions, ArrayList<Transaction> yearTransactions,
                                   MonthlyReport monthlyReport, YearlyReport yearlyReport, String[] months, String[] years) {
        for (int cntMonth = 0; cntMonth < monthTransactions.size(); cntMonth++) {
            System.out.println("Данные за " + months[cntMonth] + ":");
            System.out.println("Суммарный доход: " + monthlyReport.getSumIncomeForMonth(monthTransactions.get(cntMonth)));
            System.out.println("Общая сумма расходов: " + monthlyReport.getSumExpenseForMonth(monthTransactions.get(cntMonth)));
        }
        for (int cntYear = 0; cntYear < years.length; cntYear++) {
            int check = checkData(monthTransactions, yearTransactions, monthlyReport, yearlyReport, cntYear);
            if (check != 0)
                System.out.println("Несоответствие данных выявлено в " + months[check - 1] + " " + years[cntYear] + " года");
        }
    }

    static void printInfoAboutMonthReports (ArrayList<Transaction> monthTransactions, MonthlyReport monthlyReport, String[] months) {
        for (int cntMonth = 0; cntMonth < monthTransactions.size(); cntMonth++) {
            int bestIncome = monthlyReport.getBestIncomeMonthByMonth(monthTransactions.get(cntMonth));
            String bestIncomeName = monthlyReport.getBestIncomeNameMonthByMonth(monthTransactions.get(cntMonth));
            int biggestExpense = monthlyReport.getBiggestExpenseMonthByMonth(monthTransactions.get(cntMonth));
            String biggestExpenseName = monthlyReport.getBiggestExpenseNameMonthByMonth(monthTransactions.get(cntMonth));
            System.out.println("Данные за " + months[cntMonth] + ":");
            System.out.println("Самый прибыльный товар: " + bestIncomeName + ". Продан за " + bestIncome + ".");
            System.out.println("Самая большая трата: " + biggestExpenseName + ". Потрачено " + biggestExpense + ".");
        }
    }

    static void printInfoAboutYearReports (ArrayList<Transaction> yearTransactions, YearlyReport yearlyReport, String[] months, String[] years) {
        for (int cntYear = 0; cntYear < yearTransactions.size(); cntYear++) {
            double commonIncome = yearlyReport.getCommonIncomePerYear(yearTransactions.get(cntYear));
            double commonExpense = yearlyReport.getCommonExpensePerYear(yearTransactions.get(cntYear));
            System.out.println("Данные за " + months.length + " месяца " + years[cntYear] + " года:");
            for (int cntMonth = 0; cntMonth < months.length; cntMonth++) {
                System.out.println("Прибыль за " + months[cntMonth] + " составляет " + yearlyReport.getIncomePerMonth(yearTransactions.get(cntYear), cntMonth+1));
            }
            System.out.println("Cредний расход за все имеющиеся операции в " + years[cntYear] + " году: " + (commonExpense/months.length));
            System.out.println("Cредний доход  за все имеющиеся операции в " + years[cntYear] + " году: " + (commonIncome/months.length));
        }
    }
    static int checkData (ArrayList<Transaction> monthTransactions, ArrayList<Transaction> yearTransactions, MonthlyReport monthlyReport, YearlyReport yearlyReport, int cntYear) {
        for (int cntMonth = 0; cntMonth < monthTransactions.size(); cntMonth++) {
            int incomeInMonthReport = monthlyReport.getSumIncomeForMonth(monthTransactions.get(cntMonth));
            int incomeInYearReport = yearlyReport.getIncomePerMonth(yearTransactions.get(cntYear), cntMonth+1);
            int expenseInMonthReport = monthlyReport.getSumExpenseForMonth(monthTransactions.get(cntMonth));
            int expenseInYearReport = yearlyReport.getExpensePerMonth(yearTransactions.get(cntYear),cntMonth+1) ;
            if (incomeInMonthReport != incomeInYearReport || expenseInMonthReport != expenseInYearReport)
                return cntMonth + 1;
        }
        return 0;
    }

}