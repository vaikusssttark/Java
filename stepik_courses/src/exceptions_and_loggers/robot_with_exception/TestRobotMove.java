package exceptions_and_loggers.robot_with_exception;

//Реализация выброса своего исключения и обработка других по опр. условиям

public class TestRobotMove {
    public static void main(String[] args) {

        RobotConnection rc = new RobotConnection() {
            @Override
            public void moveRobotTo(int x, int y) {
                System.out.println("move: (" + x + "," + y + ")");
            }

            @Override
            public void close() {
                System.out.println("Closed");
            }
        };

        moveRobot(() -> rc, 65, 45);
    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        boolean isConnected = false;
        for (int i = 0; i < 3; i++) {
            try (RobotConnection robotConnection = robotConnectionManager.getConnection()) {
                robotConnection.moveRobotTo(toX, toY);
                isConnected = true;
                i = 3;
            } catch (Throwable e) {
                if (!e.getClass().equals(RobotConnectionException.class)) {
                    i = 3;
                    isConnected = false;
                    throw e;
                }
            }
            if (i == 2 && isConnected == true) {
                throw new RobotConnectionException("");
            }
        }
    }
}
