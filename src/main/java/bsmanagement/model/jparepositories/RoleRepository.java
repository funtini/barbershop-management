package bsmanagement.model.jparepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsmanagement.model.Role;
import bsmanagement.model.Role.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
    
    boolean existsByName(RoleName roleName);
}
