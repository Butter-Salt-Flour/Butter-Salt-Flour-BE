package practice.buttersaltflour.auth.exception;

import util.execption.BusinessBaseException;
import util.execption.ErrorCode;

public class AuthContextMissingException extends BusinessBaseException {
    public AuthContextMissingException() {
        super(ErrorCode.FIREBASE_CONTEXT_MISSING);
    }
}
