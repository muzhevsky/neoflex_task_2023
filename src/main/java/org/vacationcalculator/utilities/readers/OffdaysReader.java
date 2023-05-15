package org.vacationcalculator.utilities.readers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component("offdaysReader")
@Scope("prototype")
public class OffdaysReader implements Reader {
    private final String path = "resources\\calculator\\offdays.json";
    private Reader reader;

    @Autowired
    private OffdaysReader() throws FileNotFoundException {
        this.reader = new DefaultFileReader(path);
    }

    @Override
    @SneakyThrows
    public byte[] read() {
        return reader.read();
    }

    @Override
    @SneakyThrows
    public String readString(){
        return reader.readString();
    }
}
