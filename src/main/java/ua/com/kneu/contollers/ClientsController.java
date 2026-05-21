package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.kneu.dtos.clients.ClientsDTO;
import ua.com.kneu.services.clients.ClientsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class ClientsController implements BaseController<ClientsDTO> {

    private final ClientsService clientsService;

    @Override
    @PostMapping("save_client")
    public ResponseEntity<ClientsDTO> save(@RequestBody ClientsDTO obj) {
        ClientsDTO client = clientsService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @Override
    @PatchMapping("update_client/{id}")
    public ResponseEntity<ClientsDTO> update(@PathVariable Long id, @RequestBody ClientsDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok(clientsService.update(obj));
    }

    @Override
    @DeleteMapping("delete_client/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clientsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("delete_all_clients")
    public ResponseEntity<Void> deleteAll() {
        clientsService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("get_all_clients")
    public ResponseEntity<List<ClientsDTO>> findAll() {
        return ResponseEntity.ok(clientsService.findAll());
    }

    @Override
    @GetMapping("get_client/{id}")
    public ResponseEntity<ClientsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientsService.findById(id));
    }
}