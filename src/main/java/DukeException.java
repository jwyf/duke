public class DukeException extends Exception {
    /**
     * Exceptions specific to Duke
     */
    protected ErrorType errorType;

    DukeException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}