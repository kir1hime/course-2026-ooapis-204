package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.kneu.services.cards.CardsService;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class CardsController {
    private final CardsService cardsService;
}
