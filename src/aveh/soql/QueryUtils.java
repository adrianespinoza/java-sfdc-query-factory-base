package aveh.soql;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import aveh.database.Setup;

public class QueryUtils {
    public static void addPrefixToFieldNames(List<String> fieldApiNamesList) {
        if (!Setup.ORG_PREFIX.isEmpty()) {
            String field;
            for (int i = 0; i < fieldApiNamesList.size(); i++) {
                field = fieldApiNamesList.get(i).toLowerCase();
                if (!StringUtils.contains(field, Setup.ORG_PREFIX.toLowerCase()) && StringUtils.contains(field, Setup.SOBJECT_STANDARD_POSTFIX.toLowerCase())) {
                    String element = Setup.ORG_PREFIX + fieldApiNamesList.get(i);
                    fieldApiNamesList.set(i, element);
                }
            }
        }
    }
    public static void addPrefix(String[] fieldApiNamesList) {
        if (!Setup.ORG_PREFIX.isEmpty()) {
            String field;
            for (int i = 0; i < fieldApiNamesList.length; i++) {
                field = fieldApiNamesList[i].toLowerCase();
                if (!StringUtils.contains(field, Setup.ORG_PREFIX.toLowerCase()) && StringUtils.contains(field, Setup.SOBJECT_STANDARD_POSTFIX.toLowerCase())) {
                    String element = Setup.ORG_PREFIX + fieldApiNamesList[i];
                    fieldApiNamesList[i] = element;
                }
            }
        }
    }

    public static String removePrefix(String type) {
        String withoutPrefix = type;
        if (!Setup.ORG_PREFIX.isEmpty()) {
            withoutPrefix = withoutPrefix.replace(Setup.ORG_PREFIX, "");
        }
        return withoutPrefix;
    }

    public static String addPrefix(String type) {
        String withPrefix = type;
        type = type.toLowerCase();
        if (StringUtils.isNotBlank(Setup.ORG_PREFIX) && !StringUtils.contains(type, Setup.ORG_PREFIX.toLowerCase()) && (StringUtils.contains(type, Setup.SOBJECT_STANDARD_POSTFIX.toLowerCase()) || StringUtils.contains(type, Setup.SOBJECT_REFERENCE_POSTFIX.toLowerCase()))) {
            withPrefix = Setup.ORG_PREFIX + withPrefix;
        }
        return withPrefix;
    }

    public static String convertToReferenceField(String fieldApiName) {
        fieldApiName = fieldApiName.replace(Setup.SOBJECT_STANDARD_POSTFIX, Setup.SOBJECT_REFERENCE_POSTFIX);
        return fieldApiName;
    }
}
