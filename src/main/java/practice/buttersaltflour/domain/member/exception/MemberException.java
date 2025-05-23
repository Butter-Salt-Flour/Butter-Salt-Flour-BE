package practice.buttersaltflour.domain.member.exception;

import util.execption.BusinessBaseException;
import util.execption.ErrorCode;

public class MemberException extends BusinessBaseException {

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
    public MemberException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}