package rodl.projecttigabe.services;

import java.util.List;
import java.util.Set;

import rodl.projecttigabe.entities.User;
import rodl.projecttigabe.entities.UserRole;

public interface UserService {
    // creating user
    public User createUser(User user,Set<UserRole> userRoles) throws Exception;
    
    // get user by username 
    public User getUser(String username);

    //delete user by ud
    public void deleteUser(Long userId);

    // add byU
    public List<User> getAllUser();

}
