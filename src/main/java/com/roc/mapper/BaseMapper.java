package com.roc.mapper;

/**
 * @author p
 */
public interface BaseMapper<T> {

    int insertPojo(T t);

    T getPojo(int id);

    int  updatePojo(T t);

    int deletePojo(int id);
}
