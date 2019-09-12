package exceptions;

public class DukeException extends Exception {

    private ExceptionType exceptionType;

    /**
     * Constructor for the exceptions specific to Duke
     * @param exceptionType The exception type that is specific to Duke
     */
    public DukeException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * The getter for the exceptions specific to Duke
     * @return The exception type that is specific to Duke
     */
    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}