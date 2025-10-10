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

    @GetMapping("/")
    public ResponseEntity<UserDto> getUser(@RequestParam String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        if(userService.deleteUser(id)){
            return new ResponseEntity<>("User with id "+ id+ "deleted Successfully",HttpStatus.ACCEPTED);
        }
       else  {
            return new ResponseEntity<>("User with id "+ id+ "not found",HttpStatus.NOT_FOUND);
       }
    }
}
