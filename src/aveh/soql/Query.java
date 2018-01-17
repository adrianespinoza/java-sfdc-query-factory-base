package aveh.soql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class Query implements Soqlable {
    private List<Condition> criteria;
    private String table;
    private String[] columns;

    private String orderByColumn;
    private Order order;
    private OrdersNull ordersNull;

    private List<Soqlable> aggregateFunctions;
    private String[] groupByColumns;

    public Query() {
        init();
    }

    public static Query select(String columns) {
        columns = StringUtils.deleteWhitespace(columns);
        String[] tempColumns = StringUtils.split(columns, ",");
        return select(tempColumns);
    }

    public static Query select(String... columns) {
        Query builder = new Query();
        QueryUtils.addPrefix(columns);
        builder.columns = columns;
        return builder;
    }

    public static Query select(Condition... aggregateFunctions) {
        return select("", aggregateFunctions);
    }

    public static Query select(String summaryField, Soqlable... aggregateFunctions) {
        String[] columns = StringUtils.split(summaryField, ",");
        return select(columns, aggregateFunctions);
    }

    public static Query select(String[] summaryColumns, Soqlable... aggregateFunctions) {
        Query builder = new Query();
        QueryUtils.addPrefix(summaryColumns);
        builder.columns = summaryColumns;
        for (Soqlable condition : aggregateFunctions) {
            builder.aggregateFunctions.add(condition);
        }
        return builder;
    }

    public Query from(String entity) {
        this.table = QueryUtils.addPrefix(entity);
        return this;
    }

    public Query where(Condition... criteria) {
        for (Condition crit : criteria) {
            this.criteria.add(crit);
        }
        return this;
    }

    public Query orderBy(String column) {
        this.orderByColumn = QueryUtils.addPrefix(column);
        return this;
    }

    public Query orderBy(String column, Order order) {
        this.orderByColumn = QueryUtils.addPrefix(column);
        this.order = order;
        return this;
    }

    public Query orderBy(String column, OrdersNull ordersNull) {
        this.orderByColumn = QueryUtils.addPrefix(column);
        this.ordersNull = ordersNull;
        return this;
    }

    public Query orderBy(String column, Order order, OrdersNull ordersNull) {
        this.orderByColumn = QueryUtils.addPrefix(column);
        this.order = order;
        this.ordersNull = ordersNull;
        return this;
    }

    public Query groupBy(String columns) {
        String[] groupByColumns = StringUtils.split(columns, ",");
        QueryUtils.addPrefix(groupByColumns);
        return groupBy(groupByColumns);
    }

    public Query groupBy(String...columns) {
        this.groupByColumns = columns;
        return this;
    }

    public String toSoql() {
        Output out = new Output("    ");
        this.write(out);
        return out.toString();
    }

    @Override
    public void write(Output out) {
        out.println("SELECT");
        // Add columns to select
        if ((columns != null) && (columns.length > 0)) {
            out.indent();
            appendColumns(out, columns, ",");
            out.unindent();
        }

        // or add functions
        if ((aggregateFunctions != null) && (aggregateFunctions.size() > 0)) {
            out.indent();
            if ((columns != null) && (columns.length > 0)) {
                out.print(", ");
            }
            appendList(out, aggregateFunctions, ",");
            out.unindent();
        }

        // Add tables to select from
        out.println("FROM");

        // Determine all tables used in query
        out.indent();
        appendString(out, table);
        out.unindent();

        // Add criteria
        if (criteria.size() > 0) {
            out.println("WHERE");
            out.indent();
            appendList(out, criteria, "AND");
            out.unindent();
        }

        if ((groupByColumns != null) && (groupByColumns.length > 0)) {
            out.println("GROUP BY");
            out.indent();
            appendColumns(out, groupByColumns, ",");
            out.unindent();
        }

        if (StringUtils.isNotBlank(orderByColumn)) {
            List<String> orderExpresion = new ArrayList<String>();
            orderExpresion.add(orderByColumn);
            out.println("ORDER BY");
            out.indent();
            if (order != null) {
                orderExpresion.add(order.name());
            }
            if (ordersNull != null) {
                orderExpresion.add(ordersNull.getCode());
            }
            appendColumns(out, orderExpresion.toArray(new String[0]), "");
            out.unindent();
        }
    }

    private void appendList(Output out, Collection collection, String seperator) {
        Iterator i = collection.iterator();
        boolean hasNext = i.hasNext();

        while (hasNext) {
            Soqlable curr = (Soqlable) i.next();
            hasNext = i.hasNext();
            curr.write(out);
            out.print(' ');
            if (hasNext) {
                out.print(seperator);
            }
            out.println();
        }
    }

    private void appendColumns(Output out, String[] collection, String seperator) {
        int collectionSize = collection.length;
        for (int i = 0; i < collectionSize; i++) {
            out.print(collection[i]);
            if (i < (collectionSize - 1)) {
                out.print(seperator);
            }
            out.print(' ');
            //out.println(); for rows
        }
        out.println();// for columns
    }

    private void appendString(Output out, String value) {
        appendString(out, value, null);
    }

    private void appendString(Output out, String value, String separator) {
        out.print(value);
        if (StringUtils.isNotBlank(separator)) {
            out.print(",");
        }
        out.print(' ');
        out.println();
    }

    private void init() {
        criteria = new ArrayList<Condition>();
        aggregateFunctions = new ArrayList<Soqlable>();
    }
}
