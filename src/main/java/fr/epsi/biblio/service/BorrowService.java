package fr.epsi.biblio.service;


import fr.epsi.biblio.entity.Borrow;
import fr.epsi.biblio.repository.BookRepository;
import fr.epsi.biblio.repository.BorrowRepository;
import fr.epsi.biblio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }

    public Optional<Borrow> updateBorrow(Long id, Borrow updatedBorrow) {
        return borrowRepository.findById(id).map(borrow -> {
            borrow.setUser(updatedBorrow.getUser());
            borrow.setBook(updatedBorrow.getBook());
            borrow.setGivenBack(updatedBorrow.isGivenBack());
            return borrowRepository.save(borrow);
        });
    }

    public Optional<Borrow> patchBorrow(Long id, Map<String, Object> updates) {
        return borrowRepository.findById(id).map(borrow -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "user":
                        Long userId = ((Number) value).longValue();
                        userRepository.findById(userId).ifPresent(borrow::setUser);
                        break;
                    case "book":
                        Long bookId = ((Number) value).longValue();
                        bookRepository.findById(bookId).ifPresent(borrow::setBook);
                        break;
                    case "givenBack":
                        borrow.setGivenBack((Boolean) value);
                        break;
                }
            });
            return borrowRepository.save(borrow);
        });
    }

    public Borrow createBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }
}
