package exceptions_and_loggers.robot_with_exception;

public interface RobotConnection extends AutoCloseable {

    void moveRobotTo(int x, int y);

    @Override
    void close();
}
