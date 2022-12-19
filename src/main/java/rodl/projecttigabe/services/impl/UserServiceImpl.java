package rodl.projecttigabe.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rodl.projecttigabe.entities.User;
import rodl.projecttigabe.entities.UserRole;
import rodl.projecttigabe.helpers.UserFoundException;
import rodl.projecttigabe.repositories.RoleRepository;
import rodl.projecttigabe.repositories.UserRepository;
import rodl.projecttigabe.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if (local!=null){
            System.out.println("user is already there !");
            throw new UserFoundException();
        } else {
            for(UserRole ur:userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local=this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    
    
}
