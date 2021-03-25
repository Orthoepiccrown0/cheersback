package com.cheers.main.repository;

import com.cheers.main.model.events.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByNameIsStartingWith(String s);

}
