package aveh.soql;

import org.apache.commons.lang3.StringUtils;


public class AggregateFunction implements Soqlable {
    private String fieldName;
    private Function function;

    private AggregateFunction(Function function) {
        this.function = function;
    }

    private AggregateFunction(Function function, String fieldName) {
        this.function = function;
        this.fieldName = QueryUtils.addPrefix(fieldName);
    }

    public static AggregateFunction count() {
        AggregateFunction aggFunction = new AggregateFunction(Function.COUNT);
        return aggFunction;
    }

    public static AggregateFunction count(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction(Function.COUNT, fieldName);
        return aggFunction;
    }

    public static AggregateFunction avg(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction(Function.AVG, fieldName);
        return aggFunction;
    }

    public static AggregateFunction countDistinct(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction(Function.COUNT_DISTINCT, fieldName);
        return aggFunction;
    }

    public static AggregateFunction min(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction(Function.MIN, fieldName);
        return aggFunction;
    }

    public static AggregateFunction max(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction(Function.MAX, fieldName);
        return aggFunction;
    }

    public static AggregateFunction sum(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction(Function.SUM, fieldName);
        return aggFunction;
    }

    @Override
    public void write(Output out) {
        if (StringUtils.isNotBlank(fieldName)) {
            out.print(function + "( " + fieldName + " )");
        } else {
            out.print(function + "()");
        }
    }

}
