package aveh.soql;

public enum OrdersNull {
    NULLS_FIRST("NULLS FIRST"),
    NULLS_LAST("NULLS LAST");

    private String code;

    private OrdersNull(String ordersNull) {
        code = ordersNull;
    }

    public String getCode() {
        return code;
    }
}
