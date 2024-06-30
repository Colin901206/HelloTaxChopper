package ca.taxchopper.taxserver.common.sysenum;

public enum ErrorCodeAndMsg {

    WRONG_PASSWORD(500, "Wrong Password"),

    NEED_PASSWORD(501, "Active");

    private final Integer errorCode;

    private final String errorMessage;

    private ErrorCodeAndMsg(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer errorCode() {
        return this.errorCode;
    }

    public String errorMessage() {
        return this.errorMessage;
    }
}
