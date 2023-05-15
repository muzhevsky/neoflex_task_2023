package org.vacationcalculator.utilities.converters;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ToJsonConverter<T> implements Converter<T,String>{
    @Autowired
    @Qualifier("gson")
    private Gson gson;
    @Override
    public String convert(T from, Class<String> to) {
        return gson.toJson(from);
    }
}
