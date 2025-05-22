package practice.buttersaltflour.member.exception;

import util.execption.ErrorCode;

public class InvalidTokenTypeException extends MemberException {
    public InvalidTokenTypeException() {
        super(ErrorCode.FIREBASE_TOKEN_INVALID_TYPE);
    }
}