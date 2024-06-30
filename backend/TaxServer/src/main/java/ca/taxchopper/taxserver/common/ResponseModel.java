package ca.taxchopper.taxserver.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Common response model
 */
@Data
public class ResponseModel implements Serializable {

    private int status = 200;

    private Boolean success = true;

    private String message;

    private Object data;

    public ResponseModel() {

    }

    public ResponseModel(int status, Boolean success, String message, Object data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
