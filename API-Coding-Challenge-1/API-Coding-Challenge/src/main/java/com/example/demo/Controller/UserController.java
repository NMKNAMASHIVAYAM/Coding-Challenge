package com.example.demo.Controller;


import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.AuthRequest;
import com.example.demo.Model.UserData;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserDataService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserDataService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
    
    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserData userInfo) {
        return service.addUser(userInfo);
    }

  

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @GetMapping("/searchByUser/{userName}")
    public ResponseEntity<UserData> searchByUserName(@PathVariable("userName") String userName) {
        try {
            
            UserData userData = service.searchByUserName(userName);
            return new ResponseEntity<UserData>(userData, HttpStatus.OK);
        } catch (NoSuchElementException e) {
           
            return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/login/{user}/{password}")
	public int login(@PathVariable("user") String user, @PathVariable("password") String password) {
		int result = service.login(user, password);
		System.out.println("Controller Count  " +result);
		return result;
	}

    

}