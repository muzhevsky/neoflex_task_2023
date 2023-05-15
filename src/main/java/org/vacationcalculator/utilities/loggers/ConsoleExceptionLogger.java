package org.vacationcalculator.utilities.loggers;

import org.springframework.stereotype.Component;

@Component
public class ConsoleExceptionLogger implements ExceptionLogger {

    @Override
    public void log(Thread thread, Exception exception) {
        System.out.println("error: " + exception);
        System.out.println("stack trace: ");
        for(var elem : thread.getStackTrace()) {
            System.out.println(elem);
        }
    }
}
