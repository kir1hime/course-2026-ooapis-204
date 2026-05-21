package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.users.UsersDTO;
import ua.com.kneu.services.users.UsersService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class UsersController implements BaseController<UsersDTO> {

    private final UsersService usersService;

    @Override
    @PostMapping("save_user")
    public ResponseEntity<UsersDTO> save(@RequestBody UsersDTO obj) {
        UsersDTO user = usersService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    @PatchMapping("update_user/{id}")
    public ResponseEntity<UsersDTO> update(@PathVariable Long id, @RequestBody UsersDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(usersService.update(obj));
    }

    @Override
    @DeleteMapping("delete_user/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_users")
    public ResponseEntity<Void> deleteAll() {
        usersService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_users")
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @Override
    @GetMapping("get_user/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.findById(id));
    }
}