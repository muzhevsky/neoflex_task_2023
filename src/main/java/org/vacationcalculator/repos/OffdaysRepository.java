package org.vacationcalculator.repos;

import lombok.SneakyThrows;
import org.vacationcalculator.models.OffdaysModel;
import org.vacationcalculator.utilities.converters.Converter;
import org.vacationcalculator.utilities.readers.Reader;
import org.vacationcalculator.utilities.writers.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("offdaysRepository")
public class OffdaysRepository implements Repository<OffdaysModel, Integer> {
    @Autowired
    @Qualifier("offdaysReader")
    Reader reader;

    @Autowired
    @Qualifier("offdaysWriter")
    Writer writer;

    @Autowired
    @Qualifier("fromJsonConverter")
    Converter<String, OffdaysModel[]> deserializer;

    @Autowired
    @Qualifier("toJsonConverter")
    Converter<OffdaysModel[], String> serializer;

    @Override
    @SneakyThrows
    public OffdaysModel[] getAll() {
        var offDaysString = reader.readString();
        return deserializer.convert(offDaysString, OffdaysModel[].class);
    }

    @Override
    public OffdaysModel getById(Integer id) {
        var models = getAll();
        for(var model : models)
            if (model.getYear() == id)
                return model;

        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false; // TODO: implement method (its unnecessary for task)
    }

    @Override
    public boolean save(OffdaysModel entity) {
        var models = getAll();
        for(var i = 0; i < models.length; i++){
            if (models[i].getYear() == entity.getYear()){
                models[i] = entity;
                return writer.write(serializer.convert(models, String.class));
            }
        }

        var newModels = new OffdaysModel[models.length << 1];
        newModels[models.length] = entity;
        return writer.write(serializer.convert(newModels, String.class));
    }
}