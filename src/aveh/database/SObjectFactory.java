package aveh.database;

import java.util.ArrayList;
import java.util.List;
import com.sforce.soap.partner.sobject.SObject;

public class SObjectFactory {
    public static SObject[] buildSObjects(String sObjectApiName, String fromCsvFile) {
        SObject[] objsResultList = null;

        SObjectWrapper objWrapper = SObjectWrapperHandler.getSObject(sObjectApiName);
        List<List<String>> dataFromCsvFile = CsvFileReader.readFile1(fromCsvFile);

        if (dataFromCsvFile != null && !dataFromCsvFile.isEmpty()) {
            List<String> sobjecApiNamesList = valuesToLowercase(new ArrayList<String>(objWrapper.getFieldApiNames()));
            List<String> csvApiNamesList = valuesToLowercase(dataFromCsvFile.get(0));

            boolean containsAll = sobjecApiNamesList.containsAll(csvApiNamesList);
            if (containsAll) {
                System.out.println("csv fields: " + csvApiNamesList);
                System.out.println("obj fields: " + sobjecApiNamesList);

                objsResultList = mappingDataToSObjects(sObjectApiName, dataFromCsvFile);
            } else {
                System.out.println("csv fields dont correnpond to object");
                System.out.println("csv fields: " + csvApiNamesList);
                System.out.println("obj fields: " + sobjecApiNamesList);
            }
        } else {
            System.out.println("Problems on csv reader: " + dataFromCsvFile);
        }
        return objsResultList;
    }

    public static SObject[] mappingDataToSObjects(String sObjectApiName, List<List<String>> dataFromCsvFile) {
        List<String> fieldApiNamesList = dataFromCsvFile.get(0);//Always the first element are field api names
        SObjectUtils.addPrefixToFieldNames(fieldApiNamesList);// try to put the org prefix to field names

        int fieldValuesSize = fieldApiNamesList.size();
        int dataValuesSize = dataFromCsvFile.size();
        int startIndex = 1;
        int index = 0;

        SObject[] sobjectsResultList = new SObject[dataValuesSize - 1];
        for (int i = startIndex; i < dataValuesSize; i++) {
            List<String> fieldValuesList = dataFromCsvFile.get(i);
            SObject obj;
            if (fieldValuesList.size() > 0) {// at least bring one value
                obj = SObjectUtils.createSObject(sObjectApiName);
                for (int j = 0; j < fieldValuesSize; j++) {
                    obj.setField(fieldApiNamesList.get(j), fieldValuesList.get(j));
                }
                sobjectsResultList[index] = obj;
                index++;
            }
        }
        return sobjectsResultList;
    }

    public static List<String> valuesToLowercase(List<String> toConventList) {
        List<String> lowercaseValues = new ArrayList<String>();
        for (String value : toConventList) {
            lowercaseValues.add(value.toLowerCase().replaceAll("\\s+",""));
        }
        return lowercaseValues;
    }
}
