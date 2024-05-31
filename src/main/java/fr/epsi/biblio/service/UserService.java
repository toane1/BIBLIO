package fr.epsi.biblio.service;

import fr.epsi.biblio.entity.Book;
import fr.epsi.biblio.entity.Role;
import fr.epsi.biblio.entity.User;
import fr.epsi.biblio.repository.BookRepository;
import fr.epsi.biblio.repository.RoleRepository;
import fr.epsi.biblio.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RoleRepository roleRepository;

    static final String USER_NOT_FOUND = "No user found with id {}";

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setLastname(updatedUser.getLastname());
            user.setFirstname(updatedUser.getFirstname());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setRoles(updatedUser.getRoles());
            user.setBooks(updatedUser.getBooks());
            return userRepository.save(user);
        });
    }

    public Optional<User> patchUser(Long id, Map<String, Object> updates) {
        return userRepository.findById(id).map(user -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "username":
                        user.setUsername((String) value);
                        break;
                    case "lastname":
                        user.setLastname((String) value);
                        break;
                    case "firstname":
                        user.setFirstname((String) value);
                        break;
                    case "password":
                        user.setPassword((String) value);
                        break;
                    case "email":
                        user.setEmail((String) value);
                        break;
                    case "phone":
                        user.setPhone((String) value);
                        break;
                    case "roles":
                        Set<Long> roleIds = (Set<Long>) value;
                        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
                        user.setRoles(roles);
                        break;
                    case "books":
                        Set<Long> bookIds = (Set<Long>) value;
                        Set<Book> books = new HashSet<>(bookRepository.findAllById(bookIds));
                        user.setBooks(books);
                        break;
                }
            });
            return userRepository.save(user);
        });
    }

    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }
}
