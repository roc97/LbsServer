package com.roc.mapper;

public interface BaseMapper<T> {

    int insertPojo(T t);

    T getPojo(int id);

    int  updatePojo(T t);

    int deletePojo(int id);
}
