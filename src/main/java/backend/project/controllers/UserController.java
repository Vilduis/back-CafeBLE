package backend.project.controllers;

import backend.project.dtos.DTOToken;
import backend.project.dtos.DTOUser;
import backend.project.entities.Authority;
import backend.project.entities.User;
import backend.project.security.JwtUtilService;
import backend.project.security.SecurityUser;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtilService jwtUtilService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.ok(userService.listAllUser());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<DTOToken> login(@RequestBody DTOUser dtoUser)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoUser.getUsername(), dtoUser.getPassword()));

        DTOToken dtotoken = new DTOToken();
        User userFound = userService.findByUsername(dtoUser.getUsername());

        dtotoken.setUserId(userFound.getId());
        dtotoken.setAuthorities( userFound.getAuthorities().stream().map(Authority::getName).collect(Collectors.joining(";","","")) );
        dtotoken.setJwtToken(jwtUtilService.generateToken(new SecurityUser(userFound)));

        return new ResponseEntity<>(dtotoken, HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody DTOUser dtoUser)
    {
        User user = userService.insertUser(dtoUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody DTOUser dtoUser)
    {
        User user = userService.updateUser(dtoUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
