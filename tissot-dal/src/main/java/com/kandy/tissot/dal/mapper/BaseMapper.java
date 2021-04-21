package com.kandy.tissot.dal.mapper;

import java.io.Serializable;
import java.util.List;


/**
 * Created by bjkandy on 2015/12/29.
 */
public interface BaseMapper<T extends Serializable, ID extends Serializable> {

    Integer insert(T entity);

    Integer update(T entity);

    T findOne(ID id);

    List<T> findAll();

    List<T> findList(T entity);

    Long count();
}