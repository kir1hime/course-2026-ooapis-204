package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.currencies.CurrenciesDTO;
import ua.com.kneu.services.currencies.CurrenciesService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class CurrenciesController implements BaseController<CurrenciesDTO> {

    private final CurrenciesService currenciesService;

    @Override
    @PostMapping("save_currency")
    public ResponseEntity<CurrenciesDTO> save(@RequestBody CurrenciesDTO obj) {
        CurrenciesDTO currency = currenciesService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(currency);
    }

    @Override
    @PatchMapping("update_currency/{id}")
    public ResponseEntity<CurrenciesDTO> update(@PathVariable Long id, @RequestBody CurrenciesDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(currenciesService.update(obj));
    }

    @Override
    @DeleteMapping("delete_currency/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        currenciesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_currencies")
    public ResponseEntity<Void> deleteAll() {
        currenciesService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_currencies")
    public ResponseEntity<List<CurrenciesDTO>> findAll() {
        return ResponseEntity.ok(currenciesService.findAll());
    }

    @Override
    @GetMapping("get_currency/{id}")
    public ResponseEntity<CurrenciesDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(currenciesService.findById(id));
    }
}