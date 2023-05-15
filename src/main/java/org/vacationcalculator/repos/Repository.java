package org.vacationcalculator.repos;

public interface Repository<T, E> {
    T[] getAll();
    T getById(E id);
    boolean deleteById(E id);
    boolean save(T entity);
}
