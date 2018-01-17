package aveh.main;

import aveh.database.DataLoader;
import aveh.database.DatabaseConnector;
import aveh.high.Validator;
import aveh.soql.AggregateFunction;
import aveh.soql.BasicCondition;
import aveh.soql.Condition;
import aveh.soql.LogicalOperator;
import aveh.soql.Order;
import aveh.soql.OrdersNull;
import aveh.soql.Query;
import aveh.soql.SetCondition;

public class Run {
    static final String USERNAME = "adriancito@aveh.com";
    static final String PASSWORD = "Admin123";
    static final String TOKEN = "5twfCIh8uR81BzKKmC6uSGxG9";
    static String AUTHENDPOINT = "https://login.salesforce.com/services/Soap/u/28.0";
    static String AUTHENDPOINT_SANDBOX = "https://login.salesforce.com/services/Soap/u/28.0";
    public static void main(String[] args) {

        String query1 = Query.select("Library__c", "Author__c", "Title__c")
                            .from("Book__c")
                            .where(BasicCondition.equals("Author__c", "La merced"))
                            .orderBy("Id", Order.ASC, OrdersNull.NULLS_FIRST)
                            .toSoql();

        System.out.println("query1: \n" + query1);

        /*String q = "SELECT Cpny__Author__c FROM Cpny__Book__c WHERE Cpny__Author__c = 'Smith'";

        boolean res = Validator.validate("Smith", q, "Author__c");

        String query12 = Query.select("Author__c", AggregateFunction.avg("Id"))
                .from("Book__c")
                .where(BasicCondition.equals("Author__c", "La merced"))
                .groupBy("Author__c")
                .toSoql();

        System.out.println("query1: \n" + query12);


        String query2 = Query.select("Library__c, Author__c, Title__c")
                            .from("Cpny__Book__c")
                            .where(SetCondition.in("Library__c", new String[] { "post", "fedex", "goat" }))
                            .toSoql();

        System.out.println("query2: \n" + query2);

        Condition name = BasicCondition.like("Name", "a%");
        Condition id = BasicCondition.equals("id", 12345);
        Condition feet = BasicCondition.equals("Author__c", "smelly");

        String query3 = Query.select("Library__c, Author__c, Title__c")
                            .from("Book__c")
                            .where(LogicalOperator.or(name, feet))
                            .toSoql();

        System.out.println("query3: \n" + query3);

        String query4 = Query.select("Library__c, Author__c, Title__c")
                .from("Cpny__Book__c")
                .where(LogicalOperator.or(name, LogicalOperator.and(id, feet)))
                .toSoql();

        System.out.println("query4: \n" + query4);*/

        /*DatabaseConnector.connect(USERNAME, PASSWORD, TOKEN, AUTHENDPOINT);

        //DataLoader.loadData("Cpny__Book__c", "Books.csv");
        //boolean isEquals = Validator.validate("Smith").in("Cpny__Book__c").withStandardField("Author__c").equals();

        boolean isEquals = Validator.validate("La merced").inSObject("Cpny__Book__c").withReferenceField("Library__c").withStandardField("Name").equals();

        if (isEquals) {
            System.out.println("The data is correct!");
        } else {
            System.out.println("The data is not correct!");
        }

        DatabaseConnector.disconnect();*/

        //SObject[] res = SObjectFactory.buildSObjects("StagingListPrice__c", "ListPrice.csv");
        /*SObject[] res = SObjectFactory.buildSObjects("Cpny__Book__c", "Books.csv");

        for (int i = 0; i < res.length; i++) {
            System.out.println("P field: " + res[i].getField("Author__c"));
        }*/

        //CsvFileReader.readFile1("ListPrice.csv");
    }
}

