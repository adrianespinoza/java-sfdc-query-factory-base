package aveh.soql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class LogicalOperator extends Condition {
    private String operator;
    private List<Condition> criterias;

    private LogicalOperator(String operator, Condition... nextCriterias) {
        this.operator = operator;
        this.criterias = new ArrayList<Condition>();
        for (int i = 0; i < nextCriterias.length; i++) {
            this.criterias.add(nextCriterias[i]);
        }
    }

    public static LogicalOperator and(Condition...criterias) {
        LogicalOperator logicalOperator = new LogicalOperator("AND", criterias);
        return logicalOperator;
    }

    public static LogicalOperator or(Condition...criterias) {
        LogicalOperator logicalOperator = new LogicalOperator("OR", criterias);
        return logicalOperator;
    }

    public void write(Output out) {
        appendList(out, criterias, operator);
    }

    private void appendList(Output out, Collection collection, String seperator) {
        Iterator i = collection.iterator();
        boolean hasNext = i.hasNext();
        boolean hasElements = false;

        if (hasNext) {
            out.print("( ");
            hasElements = true;
        }
        while (hasNext) {
            Soqlable curr = (Soqlable) i.next();
            hasNext = i.hasNext();
            curr.write(out);
            if (hasNext) {
                out.print(' ')
                .print(seperator)
                .print(' ');
            }
        }
        if (hasElements) {
            out.print(" )");
        }
    }
}
