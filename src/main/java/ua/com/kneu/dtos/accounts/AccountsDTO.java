package ua.com.kneu.dtos.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDTO {
    private Long id;
    private String iban;
    private BigDecimal balance;
    private BigDecimal paymentLimit;
    private Long currencyId;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private Set<Long> cardIds;
}
