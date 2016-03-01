package assignment4;

public class InvalidWordException extends Exception {
	private static final long serialVersionUID = 1L;

    public InvalidWordException(String message)
    {
        super(message);
    }

    public InvalidWordException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
