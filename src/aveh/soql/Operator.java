package aveh.soql;

public enum Operator {
    EQUALS("="),
    GREATER_THAN(">"),
    GREATER_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_OR_EQUAL("<="),
    LIKE("LIKE"),
    NOT_EQUALS("!="),
    INCLUDES("INCLUDES"),
    EXCLUDES("EXCLUDES"),
    IN("IN"),
    NO_IN("NOT IN");

    private String code;

    private Operator(String operator) {
        code = operator;
    }

    public String getOperator() {
        return code;
    }
}
