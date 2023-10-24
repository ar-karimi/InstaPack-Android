package ir.instapack.android.Exceptions;

public class UnAuthorizedException {

    private int errorCode;

    public UnAuthorizedException(int errorCode){

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
