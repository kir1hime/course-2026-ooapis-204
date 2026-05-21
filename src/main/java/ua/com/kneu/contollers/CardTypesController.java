package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.card_type.CardTypesDTO;
import ua.com.kneu.services.card_types.CardTypesService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class CardTypesController implements BaseController<CardTypesDTO> {

    private final CardTypesService cardTypesService;

    @Override
    @PostMapping("save_card_type")
    public ResponseEntity<CardTypesDTO> save(@RequestBody CardTypesDTO obj) {
        CardTypesDTO cardType = cardTypesService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardType);
    }

    @Override
    @PatchMapping("update_card_type/{id}")
    public ResponseEntity<CardTypesDTO> update(@PathVariable Long id, @RequestBody CardTypesDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(cardTypesService.update(obj));
    }

    @Override
    @DeleteMapping("delete_card_type/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cardTypesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_card_types")
    public ResponseEntity<Void> deleteAll() {
        cardTypesService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_card_types")
    public ResponseEntity<List<CardTypesDTO>> findAll() {
        return ResponseEntity.ok(cardTypesService.findAll());
    }

    @Override
    @GetMapping("get_card_type/{id}")
    public ResponseEntity<CardTypesDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardTypesService.findById(id));
    }
}