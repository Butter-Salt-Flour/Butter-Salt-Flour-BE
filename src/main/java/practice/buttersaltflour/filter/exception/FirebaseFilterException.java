package practice.buttersaltflour.filter.exception;

import util.execption.BusinessBaseException;
import util.execption.ErrorCode;

public class FirebaseFilterException extends BusinessBaseException {

    public FirebaseFilterException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FirebaseFilterException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}