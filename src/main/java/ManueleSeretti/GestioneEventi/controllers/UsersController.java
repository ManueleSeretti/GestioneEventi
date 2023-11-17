package ManueleSeretti.GestioneEventi.controllers;


/* -------------------------- USERS CRUD ------------------------------------

1. GET http://localhost:3001/users (+ query params opzionali)
2. POST http://localhost:3001/users (+ body)
3. GET http://localhost:3001/users/:id
4. PUT http://localhost:3001/users/:id (+ body)
5. DELETE http://localhost:3001/users/:id

*/

import ManueleSeretti.GestioneEventi.Entities.User;
import ManueleSeretti.GestioneEventi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    @GetMapping("")
    public Page<User> getUser(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy) {
        return usersService.getUsers(page, size, orderBy);
    }

    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser) {
        return currentUser;
    }

    ;


    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void getProfile(@AuthenticationPrincipal User currentUser) {
        usersService.findByIdAndDelete(currentUser.getId());
    }

    ;


    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return usersService.findById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable int id) {
        usersService.findByIdAndDelete(id);
    }


}
