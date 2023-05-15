package org.vacationcalculator.utilities.converters;

public interface Converter<F, T> {
    T convert(F from, Class<T> to);
}
