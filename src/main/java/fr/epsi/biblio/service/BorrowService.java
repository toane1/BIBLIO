package fr.epsi.biblio.service;


import fr.epsi.biblio.entity.Borrow;
import fr.epsi.biblio.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;


    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }

    public Borrow createBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }
}
