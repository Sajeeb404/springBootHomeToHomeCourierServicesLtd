package com.example.HomeToHomeSpring.controller;

import com.example.HomeToHomeSpring.model.BranchModel;
import com.example.HomeToHomeSpring.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class BranchController {



    @Autowired
    BranchService branchService;
    @GetMapping("/getBranch")
    public List<BranchModel> getAll(){
        return branchService.getAll();
    }

    @PostMapping("/postBranch")
    public void newPost(@RequestBody BranchModel branchModel) {
        branchService.save(branchModel);
    }


    @PutMapping("/updateBranch")
    public void update(@RequestBody BranchModel branchModel){
        branchService.save(branchModel);
    }

    @DeleteMapping("/deleteBranch/{id}")
    public void deletes(@PathVariable("id") Long id){
        branchService.del(id);
    }



    @GetMapping("/getBranch/{id}")
    public BranchModel getById(@PathVariable("id") Long id){
        return branchService.getById(id);
    }


    @GetMapping("/distanceCount")
    public static double distance(@RequestParam(value = "lat1") double lat1,@RequestParam(value = "lon1") double lon1,@RequestParam(value = "lat2") double lat2,@RequestParam(value = "lon2") double lon2,@RequestParam(value = "unit") String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                //dist = dist * 1.609344;
                dist = dist * 1.969344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

//    @GetMapping("/distanceCounts")
//    public static double distance(@RequestParam(value = "lat1") double lat1,@RequestParam(value = "lon1") double lon1,@RequestParam(value = "lat2") double lat2,@RequestParam(value = "lon2") double lon2) {
//
//        final int R = 6371; // Radius of the earth
//
//        double latDistance = Math.toRadians(lat2 - lat1);
//        double lonDistance = Math.toRadians(lon2 - lon1);
//        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = (R * c * 1000)*1000; // convert to meters
//
////        double height = el1 - el2;
////
////        distance = Math.pow(distance, 2) + Math.pow(height, 2);
//
//        return Math.sqrt(distance);
//    }
//
//       System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
//    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
//    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");


}
