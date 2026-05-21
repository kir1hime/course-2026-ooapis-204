package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.admins.AdminsDTO;
import ua.com.kneu.services.admins.AdminsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class AdminsController implements BaseController<AdminsDTO> {

    private final AdminsService adminService;

    @Override
    @PostMapping("save_admin")
    public ResponseEntity<AdminsDTO> save(@RequestBody AdminsDTO obj) {
        AdminsDTO admin = adminService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }

    @Override
    @PatchMapping("update_admin/{id}")
    public ResponseEntity<AdminsDTO> update(@PathVariable Long id, @RequestBody AdminsDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(adminService.update(obj));
    }

    @Override
    @DeleteMapping("delete_admin/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_admins")
    public ResponseEntity<Void> deleteAll() {
        adminService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_admins")
    public ResponseEntity<List<AdminsDTO>> findAll() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @Override
    @GetMapping("get_admin/{id}")
    public ResponseEntity<AdminsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.findById(id));
    }
}