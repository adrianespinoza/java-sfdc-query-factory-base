package aveh.database;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class QueryFactory {
    public static String BuildQuery(List<String> fieldList, String objectType) {
        return BuildQuery(fieldList, objectType, null, null, null, -1, -1);
    }

    public static String BuildQuery(List<String> fieldList, String objectType, String conditionExpression) {
        return BuildQuery(fieldList, objectType, conditionExpression, null, null, -1, -1);
    }

    public static String BuildQuery(List<String> fieldList, String objectType, String conditionExpression, String fieldOrderBy) {
        return BuildQuery(fieldList, objectType, conditionExpression, fieldOrderBy, null, -1, -1);
    }

    public static String BuildQuery(List<String> fieldList, String objectType, String conditionExpression, String fieldOrderBy, String sortDirection) {
        return BuildQuery(fieldList, objectType, conditionExpression, fieldOrderBy, sortDirection, -1, -1);
    }

    public static String BuildQuery(List<String> fieldList, String objectType, String conditionExpression, String fieldOrderBy, String sortDirection, int numberOfRows) {
        return BuildQuery(fieldList, objectType, conditionExpression, fieldOrderBy, sortDirection, numberOfRows, -1);
    }

    /**
     * Build a string query
     *
     * @param fieldList The field api names list to SELECT clause.
     * @param objectType The Object api name to FROM clause.
     * @param conditionExpression The condition expression to WHERE clause.
     * @param fieldOrderBy The field expression to ORDER BY clause.
     * @param sortDirection Specifies whether the results are ordered in ascending (ASC) or descending (DESC) order. Default order is ascending.
     * @param numberOfRows The maximum number of rows to return LIMIT clause
     * @param numberOfRowsToSkip The starting row offset into the result set returned OFFSET clause.
     * @return A string value
     */
    public static String BuildQuery(List<String> fieldList, String objectType, String conditionExpression, String fieldOrderBy, String sortDirection, int numberOfRows, int numberOfRowsToSkip) {
        String query = null;
        if (fieldList != null && !fieldList.isEmpty() && StringUtils.isNotBlank(objectType)) {
            objectType = SObjectUtils.addPrefix(objectType);
            SObjectUtils.addPrefixToFieldNames(fieldList);

            String joinQueryFields = StringUtils.join(fieldList, ",");
            query = (joinQueryFields.contains("Id") ? "SELECT " : "SELECT Id,") + joinQueryFields;
            query += " FROM " + objectType;
            query += StringUtils.isEmpty(conditionExpression) ? "" : " WHERE " + conditionExpression;
            query += StringUtils.isEmpty(fieldOrderBy) ? "" : " ORDER BY " + fieldOrderBy + (StringUtils.isEmpty(sortDirection) ? "" : " " + sortDirection);
            query += numberOfRows > 0 ? " LIMIT " + numberOfRows : "";
            query += numberOfRowsToSkip > 0 ? " OFFSET " + numberOfRowsToSkip : "";
        }
        return query;
    }
}
