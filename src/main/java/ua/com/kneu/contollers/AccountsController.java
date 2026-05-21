package ua.com.kneu.contollers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.kneu.services.accounts.AccountsService;

@RestController
@RequestMapping("api/v1/payments")
@AllArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    
}
