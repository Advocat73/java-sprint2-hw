public class MonthTotalPerYear {
    String[] months;
    String[] years;
    MonthTotalPerYear () {
        months = new String[] {"Янваарь", "Февраль", "Март"};
        years = new String[] {"2021"};
    }

    String[] getListMonths () {
        return months;
    }

    String[] getListYears () {
        return years;
    }
}
