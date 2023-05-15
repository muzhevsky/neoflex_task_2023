package org.vacationcalculator.utilities.writers;

import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultFileWriter implements Writer{
    private Path path;
    public DefaultFileWriter(String filePath) throws FileNotFoundException{
        if (filePath.length() == 0)
            throw new FileNotFoundException();

        var path = Paths.get(filePath);

        if (Files.notExists(path))
            throw new FileNotFoundException();

        this.path = path;
    }
    @Override
    @SneakyThrows
    public boolean write(byte[] data) {
        Files.write(path, data);
        return true;
    }

    @Override
    @SneakyThrows
    public boolean write(String data) {
        Files.write(path, data.getBytes());
        return true;
    }
}
