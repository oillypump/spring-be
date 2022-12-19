package rodl.projecttigabe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rodl.projecttigabe.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
