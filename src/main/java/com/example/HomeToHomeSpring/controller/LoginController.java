package com.example.HomeToHomeSpring.controller;

import com.example.HomeToHomeSpring.dto.*;
import com.example.HomeToHomeSpring.model.BranchModel;
import com.example.HomeToHomeSpring.model.Role;
import com.example.HomeToHomeSpring.model.User;
import com.example.HomeToHomeSpring.repository.RoleDao;
import com.example.HomeToHomeSpring.repository.UserDao;
import com.example.HomeToHomeSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {


    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;


    @Autowired
    UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

//    @GetMapping({"/getUser"})
//    public List<Role> getAll(@RequestParam(value = "roleName") String rolename) {
//        return roleDao.findbyRoleName(rolename);
//    }
    @GetMapping({"/getUser"})
    public Iterable<User> getAll() {
        return userDao.findAll();
    }


    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userDao.save(user);
    }


    @PutMapping("/updateUser")
    public void updateUsers(@RequestBody User user){
        userDao.save(user);
    }

    @DeleteMapping({"/user"})
    public ResponseEntity<?> delete(@RequestParam (value = "username") String username, @RequestParam(value = "status", required = false) Boolean status) {
        User user2 = new User();
        user2 = userDao.findByUserName(username).get();
        if (status!=null){
            user2.setEnabled(status);
        }else {
            user2.setEnabled(false);
        }
        userDao.save(user2);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @DeleteMapping("/userPdelete/{id}")
    public void deletes(@PathVariable("id") String id){
        userService.del(id);
    }




    @PutMapping({"/user"})
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestParam(value = "delete", required = false) String delete ) {

//        if (userDao.existsByUserName(user.getUserName())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//        }
//        if (userDao.existsByEmail(user.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//        }
        if (user.getRole()!=null){
            Set<Role> userRoles = new HashSet<>();

            for (Role r: user.getRole()
                 ) {
                Optional<Role> role = roleDao.findById(r.getRoleName());
                role.ifPresent(userRoles::add);
            }
            user.setRole(userRoles);
        }else {
            Role role = roleDao.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
        }
        userDao.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleDao.save(role);
    }
    @GetMapping({"/role"})
    public List<Role> getAllRole() {
        return roleDao.findAll();
    }


    @PostMapping({"/signin"})
    public ResponseEntity<?> createJwtToken22(@RequestBody LoginRequest loginRequest) throws Exception {
        User user = userDao.findByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()).get();
        return ResponseEntity.ok(new JwtResponse(user));
    }


    @PostMapping({"/signup"})
    public ResponseEntity<?> registerNewUserNew(@RequestBody SignupRequest signupRequest) {
        User user = new User();
        user.setUserName(signupRequest.getUsername());
        user.setUserFullName(signupRequest.getUserfullname());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        if (userDao.existsByUserName(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userDao.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(user.getPassword());
        userDao.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @PostMapping({"/empRegi"})
    public ResponseEntity<?> registerNewEmpNew(@RequestBody EmployeeRegistra employeeRegistra) {
        User user = new User();
        user.setUserName(employeeRegistra.getUsername());
        user.setUserFullName(employeeRegistra.getUserfullname());
        user.setEmail(employeeRegistra.getEmail());
        user.setPassword(employeeRegistra.getPassword());
        user.setBranchName(employeeRegistra.getBranchName());
        user.setEmpCatagoryName(employeeRegistra.getEmpcatagoryname());
        user.setPhNumber(employeeRegistra.getPhnumber());
        user.setGender(employeeRegistra.getGender());

        if (userDao.existsByUserName(employeeRegistra.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userDao.existsByEmail(employeeRegistra.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Role role = roleDao.findById("Employee").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setPassword(user.getPassword());
        userDao.save(user);
        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }




}
