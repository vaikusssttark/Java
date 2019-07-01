package exceptions_and_loggers;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

//Реализация объявления логгеров и установления им ограничений. Вывод в консоль в XML-формате.

public class LogConfig {
    public static void main(String[] args) {
    }

    private static void configureLogging() {
        Logger loggerA = Logger.getLogger("org.stepic.java.logging.ClassA" );
        loggerA.setLevel(Level.ALL);

        Logger loggerB = Logger.getLogger("org.stepic.java.logging.ClassB");
        loggerB.setLevel(Level.WARNING);

        Logger loggerJava = Logger.getLogger("org.stepic.java");
        loggerJava.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new XMLFormatter());

        loggerJava.addHandler(consoleHandler);
    }
}
