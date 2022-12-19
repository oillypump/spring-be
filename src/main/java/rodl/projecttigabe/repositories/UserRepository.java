package rodl.projecttigabe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rodl.projecttigabe.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    public User findByUsername(String username);
}
