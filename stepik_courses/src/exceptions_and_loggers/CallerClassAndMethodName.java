package exceptions_and_loggers;

import java.util.Arrays;

//Реализация вывода полного имени класса и метода, в котором был вызван данный.

public class CallerClassAndMethodName {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        System.out.println(Arrays.toString(stackTrace));
        if (stackTrace.length < 3)
            return null;
        else {
            StackTraceElement throwable = stackTrace[2];
            String methodName = throwable.getMethodName();
            String className = throwable.getClassName();
            return className + "#" + methodName;
        }

    }
}
