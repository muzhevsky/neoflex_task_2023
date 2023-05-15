package org.vacationcalculator.utilities.writers;

public interface Writer {
    boolean write(byte[] data);
    boolean write(String data);
}
