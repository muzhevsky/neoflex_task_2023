package org.vacationcalculator.utilities.writers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class OffdaysWriter implements Writer{
    private final String path = "resources\\calculator\\offdays.json";
    private Writer writer;

    @Autowired
    private OffdaysWriter() throws FileNotFoundException {
        this.writer = new DefaultFileWriter(path);
    }

    @Override
    public boolean write(byte[] data) {
        writer.write(data);
        return true;
    }

    @Override
    public boolean write(String data) {
        writer.write(data);
        return true;
    }
}
