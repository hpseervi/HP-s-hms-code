package com.hms.repository;

import com.hms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByName(String name);

    @Query(
            "select p from Property p JOIN p.city c where c.name=:city"
    )
    List<Property> searchHotels(
            @Param("city") String cityName
    );
}