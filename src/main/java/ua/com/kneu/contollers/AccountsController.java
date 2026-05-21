package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.accounts.AccountsDTO;
import ua.com.kneu.services.accounts.AccountsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class AccountsController implements BaseController<AccountsDTO> {

    private final AccountsService accountsService;


    @Override
    @PostMapping("save_account")
    public ResponseEntity<AccountsDTO> save(@RequestBody AccountsDTO obj) {
        AccountsDTO account = accountsService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @Override
    @PatchMapping("update_account/{id}")
    public ResponseEntity<AccountsDTO> update(@PathVariable Long id, @RequestBody AccountsDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(accountsService.update(obj));
    }

    @Override
    @DeleteMapping("delete_account/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accountsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_accounts")
    public ResponseEntity<Void> deleteAll() {
        accountsService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_accounts")
    public ResponseEntity<List<AccountsDTO>> findAll() {
        return ResponseEntity.ok(accountsService.findAll());
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<AccountsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountsService.findById(id));
    }
}
