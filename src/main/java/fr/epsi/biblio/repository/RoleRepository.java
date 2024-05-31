package fr.epsi.biblio.repository;



import fr.epsi.biblio.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllById(Iterable<Long> ids);
}
