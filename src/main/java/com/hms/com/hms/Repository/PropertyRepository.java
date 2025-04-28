package com.hms.com.hms.Repository;

import com.hms.com.hms.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//
//import java.util.List;
//@Repository
//public interface PropertyRepository extends JpaRepository<Property, Long> {
//
//    @Query("select P from Property P JOIN P.city c where c.name=:city")
//    List<Property> searchHotel(@Param("city")String CityName);
//
//
//}

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

//    @Query("SELECT p FROM Property p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    @Query("select p from Property p JOIN p.city c JOIN p.country co where c.name=:name or co.name=:name")
    List<Property> searchHotel(@Param("name") String name);

}

