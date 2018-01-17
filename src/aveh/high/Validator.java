package aveh.high;

import aveh.soql.Query;

public class Validator {
    public static ValueComparer validate(String value) {
        ValueComparer valueComp = new ValueComparer();
        valueComp.valueTo = value;
        System.out.println("Validating the value with database: " + value);
        return valueComp;
    }

    public static boolean validate(String value, String query, String field) {
        return false;
    }

    public static boolean isEmpty(Query query) {
        return false;
    }
}
