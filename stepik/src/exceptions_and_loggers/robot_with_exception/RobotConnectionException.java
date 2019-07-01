package exceptions_and_loggers.robot_with_exception;

public class RobotConnectionException extends RuntimeException {

    public RobotConnectionException(String message) {
        super(message);
    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
