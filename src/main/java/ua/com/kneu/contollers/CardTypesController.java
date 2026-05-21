package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.kneu.services.card_types.CardTypesService;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class CardTypesController {
    private final CardTypesService cardTypesService;
}
