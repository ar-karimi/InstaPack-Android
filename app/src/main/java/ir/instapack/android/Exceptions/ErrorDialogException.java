package ir.instapack.android.Exceptions;

public class ErrorDialogException {

    private String errorMessage;

    public ErrorDialogException(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
