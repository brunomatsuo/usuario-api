package br.com.userapi.controller;

import java.util.List;

import br.com.userapi.model.User;
import br.com.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity getAllUsers(){
        List<User> users = repository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        User user = repository.findById(id).orElse(null);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        else {
            return (ResponseEntity) ResponseEntity.notFound();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePassword(@PathVariable String id, @RequestBody String password) {
        User user = repository.findById(id).orElse(null);
        if(user == null) {
            return (ResponseEntity) ResponseEntity.notFound();
        }
        user.setPassword(password);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        User user = repository.findById(id).orElse(null);
        if(user == null) {
            return (ResponseEntity) ResponseEntity.notFound();
        }
        repository.delete(user);
        return ResponseEntity.ok("Usuário removido.");
    }

}
