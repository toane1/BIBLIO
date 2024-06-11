package fr.epsi.biblio.repository;

import fr.epsi.biblio.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CopyRepository extends JpaRepository<Copy, Long> {
    Optional<Copy> findById(Long copyId);
}
