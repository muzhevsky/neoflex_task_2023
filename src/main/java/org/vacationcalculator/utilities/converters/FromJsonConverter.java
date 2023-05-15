package org.vacationcalculator.utilities.converters;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FromJsonConverter<T> implements Converter<String, T>{
    @Autowired
    @Qualifier("gson")
    private Gson gson;

    @Override
    @SneakyThrows
    public T convert(String from, Class<T> to) {
        return gson.fromJson(from, to);
    }
}
