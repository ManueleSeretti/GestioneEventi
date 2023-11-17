package ManueleSeretti.GestioneEventi.services;

import ManueleSeretti.GestioneEventi.Entities.Role;
import ManueleSeretti.GestioneEventi.Entities.User;
import ManueleSeretti.GestioneEventi.exceptions.BadRequestException;
import ManueleSeretti.GestioneEventi.exceptions.NotFoundException;
import ManueleSeretti.GestioneEventi.payload.NewUserDTO;
import ManueleSeretti.GestioneEventi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder bcrypt;


    public Page<User> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return usersRepository.findAll(pageable);
    }

    public User saveUser(NewUserDTO userDTO) throws IOException {
        usersRepository.findByEmail(userDTO.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        usersRepository.findByUsername(userDTO.username()).ifPresent(user -> {
            throw new BadRequestException(
                    "Lo username " + user.getUsername() + " è già utilizzato!");
        });
        User newUser = new User();
        newUser.setUsername(userDTO.username());
        newUser.setRole(Role.USER);
        newUser.setPassword(bcrypt.encode(userDTO.password()));
        newUser.setEmail(userDTO.email());
        return usersRepository.save(newUser);
    }

    public User findById(long id) throws NotFoundException {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) throws NotFoundException {
        User found = this.findById(id);
        usersRepository.delete(found);
    }


    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }

}
