package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.UserDto;
import com.hms.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository userRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public UserService(AppUserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> createNewUser(UserDto userDto) {


        Optional<AppUser> opUsername = userRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username already exists", HttpStatus.CONFLICT);
        }

        Optional<AppUser> opEmail = userRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email already exists", HttpStatus.CONFLICT);
        }

        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        userDto.setPassword(encryptedPassword);
        AppUser appUser = modelMapper.map(userDto, AppUser.class);
        appUser.setRole("ROLE_USER");
        AppUser newUser = userRepository.save(appUser);
        UserDto savedDto = modelMapper.map(newUser, UserDto.class);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> opUsername = userRepository.findByUsername(dto.getUsername());
        if (opUsername.isPresent()) {
            AppUser appUser = opUsername.get();
            if (BCrypt.checkpw(dto.getPassword(), appUser.getPassword())) {
                //generate Token
                String token = jwtService.generateToken(appUser.getUsername());
                return token;
            } else {
                return null;
            }

        }else {
            return null;
        }
    }

    public ResponseEntity<?> createNewOwner(@Valid UserDto userDto) {
        Optional<AppUser> opUsername = userRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username already exists", HttpStatus.CONFLICT);
        }

        Optional<AppUser> opEmail = userRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email already exists", HttpStatus.CONFLICT);
        }

        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        userDto.setPassword(encryptedPassword);
        AppUser appUser = modelMapper.map(userDto, AppUser.class);
        appUser.setRole("ROLE_OWNER");
        AppUser newUser = userRepository.save(appUser);
        UserDto savedDto = modelMapper.map(newUser, UserDto.class);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
}
