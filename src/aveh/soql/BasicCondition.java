package aveh.soql;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BasicCondition extends Condition {
    //private Column column;
    private String column;
    private String matchType;
    private String value;

    private BasicCondition(String column, String matchType, boolean value) {
        setup(QueryUtils.addPrefix(column), matchType, String.valueOf(value));
    }
    private BasicCondition(String column, String operator, Date operand) {
        this(column, operator, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(operand));
    }

    private BasicCondition(String column, String matchType, float value) {
        setup(QueryUtils.addPrefix(column), matchType, String.valueOf(value));
    }

    private BasicCondition(String column, String matchType, int value) {
        setup(QueryUtils.addPrefix(column), matchType, String.valueOf(value));
    }

    private BasicCondition(String column, String matchType, String value) {
        setup(QueryUtils.addPrefix(column), matchType, quote(value));
    }
    /**EQUALS*/
    public static BasicCondition equals(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition equals(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition equals(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition equals(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition equals(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.EQUALS.getOperator(), value);
        return basicCondition;
    }

    public void write(Output out) {
        out.print(column).print(' ').print(matchType).print(' ').print(value);
    }
    /**NOT EQUALS*/
    public static BasicCondition notEquals(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.NOT_EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition notEquals(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.NOT_EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition notEquals(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.NOT_EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition notEquals(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.NOT_EQUALS.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition notEquals(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.NOT_EQUALS.getOperator(), value);
        return basicCondition;
    }

    /**GREATER_THAN*/
    public static BasicCondition greaterThan(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterThan(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterThan(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterThan(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greatherThan(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_THAN.getOperator(), value);
        return basicCondition;
    }

    /**GREATER_OR_EQUAL*/
    public static BasicCondition greaterOrEqual(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterOrEqual(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterOrEqual(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterOrEqual(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition greaterOrEqual(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.GREATER_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }

    /**LESS_THAN*/
    public static BasicCondition lessThan(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessThan(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessThan(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessThan(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_THAN.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessThan(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_THAN.getOperator(), value);
        return basicCondition;
    }

    /**LESS_OR_EQUAL*/
    public static BasicCondition lessOrEqual(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessOrEqual(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessOrEqual(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessOrEqual(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition lessOrEqual(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LESS_OR_EQUAL.getOperator(), value);
        return basicCondition;
    }

    /**LIKE*/
    public static BasicCondition like(String column, String value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LIKE.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition like(String column, boolean value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LIKE.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition like(String column, Date value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LIKE.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition like(String column, float value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LIKE.getOperator(), value);
        return basicCondition;
    }
    public static BasicCondition like(String column, int value) {
        BasicCondition basicCondition = new BasicCondition(column, Operator.LIKE.getOperator(), value);
        return basicCondition;
    }

    private void setup(String column, String matchType, String value) {
        this.column = column;
        this.matchType = matchType;
        this.value = value;
    }
}
