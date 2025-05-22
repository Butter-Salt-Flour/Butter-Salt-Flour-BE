package practice.buttersaltflour.filter.exception;

import util.execption.ErrorCode;

public class FirebaseAuthHeaderMissingException extends FirebaseFilterException{
    public FirebaseAuthHeaderMissingException() {
        super(ErrorCode.FIREBASE_AUTH_HEADER_MISSING);
    }
}
