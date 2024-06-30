package ca.taxchopper.taxserver.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Common business exception handler
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(GlobaException.class)
    public ResponseModel handleHttpMessageNotReadableException(GlobaException globaException) {
        globaException.printStackTrace();
        log.error("System error : " + globaException);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseModel.setSuccess(false);
        responseModel.setMessage(globaException.getMessage());
        responseModel.setData(null);
        return responseModel;
    }

    /**
     * Parameters validation error handler
     *
     * @param manve
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel handleValidationExceptions(MethodArgumentNotValidException manve) {
        manve.printStackTrace();
//        Map<String, String> errors = new HashMap<>();
//        manve.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.BAD_REQUEST.value());
        responseModel.setSuccess(false);
        responseModel.setMessage("Incorrect input arguments, please check.");
        return responseModel;
    }

    /**
     * Other exception handler
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseModel handleHttpRequestMethodNotSupportedException(Exception exception) {
        exception.printStackTrace();
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseModel.setSuccess(false);
        responseModel.setMessage("System internal error, please contect system admin");
        responseModel.setData(null);
        return responseModel;
    }
}
