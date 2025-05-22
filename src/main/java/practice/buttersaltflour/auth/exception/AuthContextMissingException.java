package practice.buttersaltflour.auth.exception;

import util.execption.ErrorCode;

public class AuthContextMissingException extends MemberException {
    public AuthContextMissingException() {
        super(ErrorCode.FIREBASE_CONTEXT_MISSING);
    }
}
