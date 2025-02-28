package dev.abhi.productcatalog.Repository;

public interface CustomQueries {
    String FIND_BY_ALL_NAME = "select * from categories where name = :name" ;

    String FIND_ALL_CATEGORY = "select * from categories " ;
}
