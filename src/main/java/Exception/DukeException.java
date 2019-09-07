package Exception;

public class DukeException extends Exception {
    /**
     * Exceptions specific to Duke
     */
    protected ExceptionType exceptionType;

    public DukeException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}