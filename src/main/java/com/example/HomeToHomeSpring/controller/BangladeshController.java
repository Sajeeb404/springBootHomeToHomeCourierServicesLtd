package com.example.HomeToHomeSpring.controller;


import com.example.HomeToHomeSpring.model.bangladesh.Districts;
import com.example.HomeToHomeSpring.model.bangladesh.Upazilas;
import com.example.HomeToHomeSpring.service.BangladeshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class BangladeshController {

    @Autowired
    BangladeshService bangladeshService;


    @GetMapping("/districts")
    public List<Districts> getDistricts(@RequestParam(value = "dis_id", required = false) Long id){
        if (id==null){
            return bangladeshService.getAllDistrict();
        }
        return bangladeshService.getAllByDistrictsByid(id);
    }


    @GetMapping("/upazilas")
    public List<Upazilas> getUpazilas(@RequestParam(value = "dis_id", required = false) Long id){
        if (id==null){
            return bangladeshService.getAllUpazilas();
        }
        return bangladeshService.getAllUpazilasByDistrict(id);
    }


//    @GetMapping("/getDistrict")
//    public List<Object[]> getDistrict(){
//        return bangladeshService.getAll();
//    }

}
