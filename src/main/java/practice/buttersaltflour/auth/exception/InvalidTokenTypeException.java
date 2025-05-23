package practice.buttersaltflour.auth.exception;

import util.execption.BusinessBaseException;
import util.execption.ErrorCode;

public class InvalidTokenTypeException extends BusinessBaseException {
    public InvalidTokenTypeException() {
        super(ErrorCode.FIREBASE_TOKEN_INVALID_TYPE);
    }
}