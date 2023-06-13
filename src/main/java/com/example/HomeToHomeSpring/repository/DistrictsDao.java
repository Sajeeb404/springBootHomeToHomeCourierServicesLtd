package com.example.HomeToHomeSpring.repository;


import com.example.HomeToHomeSpring.model.bangladesh.Districts;
import com.example.HomeToHomeSpring.model.bangladesh.Upazilas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictsDao extends JpaRepository<Districts, Long> {

//    @Query(value = "select d.name dName, u.name uName, d.lat, d.lon from districts d LEFT JOIN upazilas u ON u.district_id = d.id ", nativeQuery = true)
//    List<Object[]> findAllBydistrict();

    @Query(value = "select * from districts where id = :dis_id ;", nativeQuery = true)
    List<Districts> findAllByDistrict(@Param("dis_id") Long id);



}
