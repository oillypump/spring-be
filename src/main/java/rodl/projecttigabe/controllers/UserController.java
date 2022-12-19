package rodl.projecttigabe.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import rodl.projecttigabe.entities.Role;
import rodl.projecttigabe.entities.User;
import rodl.projecttigabe.entities.UserRole;
import rodl.projecttigabe.helpers.UserFoundException;
import rodl.projecttigabe.services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register")
    public User createUser(@RequestBody User user) throws Exception {
        
        user.setProfile("default.png");
            //encoding pass with bcrypt
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();
        
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);
        return this.userService.createUser(user, roles);
    }

    // get user 
    @GetMapping(value = "/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    //delete user by id
    @DeleteMapping(value = "/{userID}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());

    }


    //additional by self
    @GetMapping(value = "/alluser")
    public List<User> getAllUser(){

        List<User> userList = userService.getAllUser();
        return userList;
    }
}
