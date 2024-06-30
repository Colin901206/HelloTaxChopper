package ca.taxchopper.taxserver.common.sysenum;

public enum StatusEnum {

    INACTIVE("0", "Inactive"),

    ACTIVE("1", "Active");

    private final String value;

    private final String describe;

    private StatusEnum(String value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    public String value() {
        return this.value;
    }

    public String describe() {
        return this.describe;
    }
}
