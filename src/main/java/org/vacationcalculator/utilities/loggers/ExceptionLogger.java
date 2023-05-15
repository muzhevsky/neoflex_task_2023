package org.vacationcalculator.utilities.loggers;

public interface ExceptionLogger {
    void log(Thread thread, Exception exception);
}
