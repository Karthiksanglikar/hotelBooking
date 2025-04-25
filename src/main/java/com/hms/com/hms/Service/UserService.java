package com.hms.com.hms.Service;

import com.hms.com.hms.Entity.Appusers;
import com.hms.com.hms.Payload.LoginDto;
import com.hms.com.hms.Repository.AppusersRepository;
import com.hms.com.hms.Payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private ModelMapper mapper;
    private AppusersRepository appusersRepository;
    private JwtService jwtService;

    public UserService(ModelMapper mapper, AppusersRepository appusersRepository, JwtService jwtService) {
        this.mapper = mapper;
        this.appusersRepository = appusersRepository;
        this.jwtService = jwtService;
    }

    public Appusers convertToEntity(UserDto dto) {
            Appusers entity = mapper.map(dto, Appusers.class);
            return entity; // Converts DTO to Entity
        }

        public UserDto convertToDto(Appusers user) {
            UserDto dto = mapper.map(user, UserDto.class);
            return dto;
        }
    public ResponseEntity<String> CreateUsers(UserDto userDto) {
    Appusers appuser=convertToEntity(userDto );
            Optional<Appusers> opemail = appusersRepository.findByEmail(appuser.getEmail());
            if (opemail.isPresent()) {
                return new ResponseEntity<>("Email id exists", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Optional<Appusers> opuser = appusersRepository.findByUsername(appuser.getUsername());
            if(opuser.isPresent()){
                return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Optional<Appusers> opmobile = appusersRepository.findByMobile(appuser.getMobile());
            if(opmobile.isPresent()){
                return new ResponseEntity<>("Mobile already exist",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        String encryptpwd = BCrypt.hashpw(appuser.getPassword(), BCrypt.gensalt(5));
        appuser.setPassword(encryptpwd);
        Appusers user1 = appusersRepository.save(appuser);
            return new ResponseEntity<>("Its added successfull go for next",HttpStatus.CREATED);
    }

    public String VerifyLogin(LoginDto loginDto) {
        Optional<Appusers> opUsername = appusersRepository.findByUsername(loginDto.getUsername());
    if (opUsername.isPresent()){
        Appusers appusers = opUsername.get();
        //login is successfull then generating token
        if (BCrypt.checkpw(loginDto.getPassword(), appusers.getPassword())){
            String token = jwtService.generateTocken(appusers.getUsername());
            return token;
        }
    }
        return null;
    }

}
