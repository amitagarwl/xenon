package com.github.amitagarwl.dao;


import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;


/**
 *  Add all your db queries in one place
 */

public interface MyDao {

    @SqlUpdate("create table something (id int primary key, name varchar(100))")
    void createSomethingTable();

    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select name from something where id = :id")
    String findNameById(@Bind("id") int id);

    @SqlQuery("select product_name from Sales where id = :id")
    String getProductName(@Bind("id") String id);


    /**
     * close with no args is used to close the connection
     */
    void close();

}
