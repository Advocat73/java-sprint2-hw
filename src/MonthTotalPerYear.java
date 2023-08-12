public class MonthTotalPerYear {
    private String[] months;
    private String[] years;
    MonthTotalPerYear () {
        months = new String[] {"Янваарь", "Февраль", "Март", "Апрель"};
        years = new String[] {"2021"};
    }

    String[] getListMonths () {
        return months;
    }

    String[] getListYears () {
        return years;
    }
}
