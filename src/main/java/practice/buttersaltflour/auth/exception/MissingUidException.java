package practice.buttersaltflour.auth.exception;

import util.execption.BusinessBaseException;
import util.execption.ErrorCode;

public class MissingUidException extends BusinessBaseException {
    public MissingUidException() {
        super(ErrorCode.FIREBASE_TOKEN_MISSING_UID);
    }
}