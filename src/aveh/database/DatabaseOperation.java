package aveh.database;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class DatabaseOperation {
    public static QueryResult query(String query) {
        QueryResult queryResults = null;
        System.out.println("Executing query: " + query);
        try {
            queryResults = DatabaseConnector.connection.query(query);
            System.out.println("The query was executed successfully!");
        } catch (ConnectionException e) {
            System.out.println("Error in query execution: " + e.getMessage());
            e.printStackTrace();
        }
        return queryResults;
    }

    public static SaveResult[] insert(SObject[] records) {
        SaveResult[] saveResults = null;
        try {
            // create the records in Salesforce.com
            saveResults = DatabaseConnector.connection.create(records);
            // check the returned results for any errors
            for (int i=0; i< saveResults.length; i++) {
                if (saveResults[i].isSuccess()) {
                    System.out.println(i+". Successfully created record - Id: " + saveResults[i].getId());
                } else {
                    Error[] errors = saveResults[i].getErrors();
                    for (int j=0; j< errors.length; j++) {
                        System.out.println("ERROR creating record: " + errors[j].getMessage());
                    }
                }
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        return saveResults;
    }

    public static DeleteResult[] delete(String[] ids) {
        DeleteResult[] deleteResults = null;
        try {
            // delete the records in Salesforce.com by passing an array of Ids
            deleteResults = DatabaseConnector.connection.delete(ids);
            // check the results for any errors
            for (int i=0; i< deleteResults.length; i++) {
                if (deleteResults[i].isSuccess()) {
                    System.out.println(i+". Successfully deleted record - Id: " + deleteResults[i].getId());
                } else {
                    Error[] errors = deleteResults[i].getErrors();
                    for (int j=0; j< errors.length; j++) {
                        System.out.println("ERROR deleting record: " + errors[j].getMessage());
                    }
                }
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        return deleteResults;
    }

    public static SaveResult[] update(SObject[] records) {
        SaveResult[] saveResults = null;
        try {
            // update the records in Salesforce.com
            saveResults = DatabaseConnector.connection.update(records);
            // check the returned results for any errors
            for (int i=0; i< saveResults.length; i++) {
                if (saveResults[i].isSuccess()) {
                    System.out.println(i+". Successfully updated record - Id: " + saveResults[i].getId());
                } else {
                    Error[] errors = saveResults[i].getErrors();
                    for (int j=0; j< errors.length; j++) {
                        System.out.println("ERROR updating record: " + errors[j].getMessage());
                    }
                }
            }
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        return saveResults;
    }

    public Field[] describeSObject(String object) {
        Field[] fields = null;
        try {
            // Make the describe call
            DescribeSObjectResult describeSObjectResult = DatabaseConnector.connection.describeSObject(object);
            // Get sObject metadata
            if (describeSObjectResult != null) {
                // Get the fields
                fields = describeSObjectResult.getFields();
            }
        } catch (ConnectionException ce) {
            ce.printStackTrace();
        }
        return fields;
    }
}
