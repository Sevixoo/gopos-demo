package com.sevixoo.goposdemo.domain.repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Seweryn on 2016-07-08.
 */
public interface IBasicRepository<T> {

    void insert( T item )throws SQLException;
    T load( long _id )throws SQLException;
    List<T> list( int offset , int limit )throws SQLException;
    void delete( T item )throws SQLException;
    void update( T item )throws SQLException;
    boolean exists( T item )throws SQLException;
}
