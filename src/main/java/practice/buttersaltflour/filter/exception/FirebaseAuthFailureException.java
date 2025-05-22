package practice.buttersaltflour.filter.exception;

import util.execption.ErrorCode;

public class FirebaseAuthFailureException extends FirebaseFilterException{
    public FirebaseAuthFailureException() {
        super(ErrorCode.FIREBASE_AUTH_FAILURE);
    }
    public FirebaseAuthFailureException(String message) {
        super(message, ErrorCode.FIREBASE_AUTH_FAILURE);
    }

}
