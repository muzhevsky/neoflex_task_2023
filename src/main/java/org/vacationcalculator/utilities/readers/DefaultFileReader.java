package org.vacationcalculator.utilities.readers;

import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultFileReader implements Reader{
    private Path path;
    public DefaultFileReader(String filePath) throws FileNotFoundException{
        if (filePath.length() == 0)
            throw new FileNotFoundException();

        var path = Paths.get(filePath);

        if (Files.notExists(path))
            throw new FileNotFoundException();

        this.path = path;
    }

    @Override
    @SneakyThrows
    public byte[] read() {
        return Files.readAllBytes(path);
    }

    @Override
    @SneakyThrows
    public String readString(){
        return new String(read());
    }
}
