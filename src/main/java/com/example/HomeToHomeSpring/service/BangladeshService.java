package com.example.HomeToHomeSpring.service;

import com.example.HomeToHomeSpring.model.bangladesh.Districts;

import com.example.HomeToHomeSpring.model.bangladesh.Upazilas;
import com.example.HomeToHomeSpring.repository.DistrictsDao;
import com.example.HomeToHomeSpring.repository.UpazilasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BangladeshService {

    @Autowired
    DistrictsDao districtsDao;
    @Autowired
    UpazilasDao upazilasDao;

      public List<Districts> getAllDistrict(){
        return districtsDao.findAll();
    }

    public List<Upazilas> getAllUpazilas(){
        return upazilasDao.findAll();
    }
    public List<Upazilas> getAllUpazilasByDistrict(Long id){
        return upazilasDao.findAllByDistrict(id);
    }

    public List<Districts> getAllByDistrictsByid(Long id){
        return districtsDao.findAllByDistrict(id);
    }

//    public List<Object[]> getAll(){
//        List<Object[]> objects = districtsDao.findAllBydistrict();
//
//        objects.forEach(objects1 -> {
//            System.out.println(objects1[0]);
//            System.out.println(objects1[1]);
//            System.out.println(objects1[2]);
//        });
//
//
//        return districtsDao.findAllBydistrict();
//    }

}
