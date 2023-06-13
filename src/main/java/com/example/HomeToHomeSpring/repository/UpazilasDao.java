package com.example.HomeToHomeSpring.repository;


import com.example.HomeToHomeSpring.model.bangladesh.Districts;
import com.example.HomeToHomeSpring.model.bangladesh.Upazilas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpazilasDao extends JpaRepository<Upazilas, Long> {



    @Query(value = "select id from districts;", nativeQuery = true)
    List<Upazilas> findAllDistrict();

    @Query(value = "select * from upazilas where district_id = :dis_id ;", nativeQuery = true)
    List<Upazilas> findAllByDistrict(@Param("dis_id") Long id);

}
