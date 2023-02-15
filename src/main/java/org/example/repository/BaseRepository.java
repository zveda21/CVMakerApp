package org.example.repository;

import java.util.List;

public interface BaseRepository<T> {

    List<T> getAll();

    T getById(int id);

    int delete(int id);

    void update(T t);

    void create(T t);

}
