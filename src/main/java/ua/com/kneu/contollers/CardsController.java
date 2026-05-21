package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.cards.CardsDTO;
import ua.com.kneu.services.cards.CardsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class CardsController implements BaseController<CardsDTO> {

    private final CardsService cardsService;

    @Override
    @PostMapping("save_card")
    public ResponseEntity<CardsDTO> save(@RequestBody CardsDTO obj) {
        CardsDTO card = cardsService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @Override
    @PatchMapping("update_card/{id}")
    public ResponseEntity<CardsDTO> update(@PathVariable Long id, @RequestBody CardsDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(cardsService.update(obj));
    }

    @Override
    @DeleteMapping("delete_card/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cardsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_cards")
    public ResponseEntity<Void> deleteAll() {
        cardsService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_cards")
    public ResponseEntity<List<CardsDTO>> findAll() {
        return ResponseEntity.ok(cardsService.findAll());
    }

    @Override
    @GetMapping("get_card/{id}")
    public ResponseEntity<CardsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardsService.findById(id));
    }
}