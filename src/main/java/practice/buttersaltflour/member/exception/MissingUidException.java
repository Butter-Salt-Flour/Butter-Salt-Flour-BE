package practice.buttersaltflour.member.exception;

import util.execption.ErrorCode;

public class MissingUidException extends MemberException {
    public MissingUidException() {
        super(ErrorCode.FIREBASE_TOKEN_MISSING_UID);
    }
}