package aveh.high;

import aveh.database.SObjectUtils;

public class ValueComparer {
    public String valueTo;
    public SObejctEntity inSObject(String sObejctApiName) {
        SObejctEntity sfo = new SObejctEntity();

        sObejctApiName = SObjectUtils.addPrefix(sObejctApiName);

        sfo.apiName = sObejctApiName;
        sfo.valueComp = this;
        return sfo;
    }
}
