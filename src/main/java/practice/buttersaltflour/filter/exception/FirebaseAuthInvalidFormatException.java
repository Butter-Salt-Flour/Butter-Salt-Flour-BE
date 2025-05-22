package practice.buttersaltflour.filter.exception;

import util.execption.ErrorCode;

public class FirebaseAuthInvalidFormatException extends FirebaseFilterException {
    public FirebaseAuthInvalidFormatException() {
        super(ErrorCode.FIREBASE_AUTH_INVALID_FORMAT);
    }
}
