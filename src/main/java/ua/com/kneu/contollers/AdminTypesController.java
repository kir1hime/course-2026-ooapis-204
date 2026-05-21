package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.admin_types.AdminTypesDTO;
import ua.com.kneu.services.admin_types.AdminTypesService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class AdminTypesController implements BaseController<AdminTypesDTO> {

    private final AdminTypesService adminTypesService;

    @Override
    @PostMapping("save_admin_type")
    public ResponseEntity<AdminTypesDTO> save(@RequestBody AdminTypesDTO obj) {
        AdminTypesDTO adminType = adminTypesService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminType);
    }

    @Override
    @PatchMapping("update_admin_type/{id}")
    public ResponseEntity<AdminTypesDTO> update(@PathVariable Long id, @RequestBody AdminTypesDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(adminTypesService.update(obj));
    }

    @Override
    @DeleteMapping("delete_admin_type/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        adminTypesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_admin_types")
    public ResponseEntity<Void> deleteAll() {
        adminTypesService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_admin_types")
    public ResponseEntity<List<AdminTypesDTO>> findAll() {
        return ResponseEntity.ok(adminTypesService.findAll());
    }

    @Override
    @GetMapping("get_admin_type/{id}")
    public ResponseEntity<AdminTypesDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adminTypesService.findById(id));
    }
}