package aveh.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SObjectWrapperHandler {
    private static Map<String, SObjectWrapper> sobjectsMap;

    public static SObjectWrapper getSObject(String sObjectApiName) {
        SObjectWrapper sObjResult;
        String sObjApiNameWithoutPrefix = SObjectUtils.removePrefix(sObjectApiName);
        String fullPath = Setup.SOBJECT_FILE_PATH + sObjApiNameWithoutPrefix + ".object";
        if (sobjectsMap != null) {
            if (sobjectsMap.containsKey(sObjApiNameWithoutPrefix)) {
                sObjResult = sobjectsMap.get(sObjApiNameWithoutPrefix);
            } else {
                sObjResult = SObjectXmlParser.readSObject(sObjApiNameWithoutPrefix, fullPath);
            }
        } else {
            sobjectsMap = new HashMap<String, SObjectWrapper>();
            sObjResult = SObjectXmlParser.readSObject(sObjApiNameWithoutPrefix, fullPath);
        }
        sobjectsMap.put(sObjApiNameWithoutPrefix, sObjResult);
        return sObjResult;
    }

    public static void showSObejcts() {
        for (SObjectWrapper obj : sobjectsMap.values()) {
            System.out.println(obj.fullName);
            showFields(new ArrayList<FieldWrapper>(obj.fieldDescribe.values()));
        }
    }

    private static void showFields(List<FieldWrapper> fields) {
        for (FieldWrapper f : fields) {
            f.showCharacateristic();
        }
    }
}
