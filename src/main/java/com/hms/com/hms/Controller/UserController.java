package com.hms.com.hms.Controller;

import com.hms.com.hms.Payload.LoginDto;
import com.hms.com.hms.Payload.TokenDto;
import com.hms.com.hms.Service.UserService;
import com.hms.com.hms.Payload.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }
    @PostMapping("/sign-up")
    public ResponseEntity<?>createUser(@RequestBody UserDto userDto){
        userService.CreateUsers(userDto);
       return new ResponseEntity <>("added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?>Login(@RequestBody LoginDto loginDto){
        String token = userService.VerifyLogin(loginDto);
        if (token!=null){
            TokenDto tokenDto=new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("jwt");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else
            return new ResponseEntity<>("Invalid-User",HttpStatus.FORBIDDEN);
    }
    @PostMapping("/signUp-PropertyOwner")
    public ResponseEntity<?>createUsers(@RequestBody UserDto userDto){
        userService.CreatePropertyOwner(userDto);
        return new ResponseEntity <>("added successfully", HttpStatus.CREATED);
    }
}
