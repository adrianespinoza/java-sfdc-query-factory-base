package aveh.high;

import aveh.database.DatabaseOperation;
import aveh.soql.BasicCondition;
import aveh.soql.Query;

import com.sforce.soap.partner.QueryResult;

public class StandardField {
    public String apiName;
    public SObejctEntity parentSObject;

    public boolean equals() {
        String query = getQuery();
        System.out.println("Query: \n" + query);
        QueryResult queryResults = DatabaseOperation.query(query);

        boolean isEquals = false;
        if (queryResults.getSize() > 0) {
            isEquals = true;
        }
        return isEquals;
        //return false;
    }

    private String getQuery() {
        String query = Query.select(apiName)
                            .from(parentSObject.apiName)
                            .where(BasicCondition.equals(apiName, parentSObject.valueComp.valueTo))
                            .orderBy("Id")
                            .toSoql();
        return query;
    }
}
