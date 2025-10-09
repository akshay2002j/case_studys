package com.example.banking_sys_with_mongo.controller;



import com.example.banking_sys_with_mongo.dto.UserDto;
import com.example.banking_sys_with_mongo.service.DaoUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class UserController {

    DaoUserService userService;

    public UserController(DaoUserService userService) {
        this.userService = userService;
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUser( @PathVariable String id) {
//        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
//    }
//
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
//        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable String id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>("User with id "+ id+ "deleted Successfully",HttpStatus.OK);
//    }
}
