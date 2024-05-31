package fr.epsi.biblio.repository;


import fr.epsi.biblio.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByBorrowId(Long borrowId);
}
