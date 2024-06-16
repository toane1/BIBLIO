package fr.epsi.biblio.Controller;


import fr.epsi.biblio.entity.Borrow;
import fr.epsi.biblio.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public List<Borrow> getAllBorrows() { return borrowService.getAllBorrows(); }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        return borrowService.getBorrowById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Borrow createBorrow(@RequestBody Borrow borrow) {
        return borrowService.createBorrow(borrow);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Long id, @RequestBody Borrow borrow) {
        return borrowService.updateBorrow(id, borrow)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Borrow> patchBorrow(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return borrowService.patchBorrow(id, updates)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }
}
