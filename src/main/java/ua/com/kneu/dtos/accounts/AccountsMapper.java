package ua.com.kneu.dtos.accounts;

import ua.com.kneu.entities.Accounts;
import ua.com.kneu.entities.Cards;

import java.util.stream.Collectors;

public class AccountsMapper {

    public static AccountsDTO toDTO(Accounts account) {
        AccountsDTO dto = new AccountsDTO();

        dto.setId(account.getId());
        dto.setIban(account.getIban());
        dto.setBalance(account.getBalance());
        dto.setPaymentLimit(account.getPaymentLimit());

        if (account.getCurrency() != null) {
            dto.setCurrencyId(account.getCurrency().getId());
        }

        if (account.getClient() != null) {
            dto.setClientId(account.getClient().getId());
            dto.setClientFirstName(account.getClient().getFirstName());
            dto.setClientLastName(account.getClient().getLastName());
        }

        if (account.getCards() != null) {
            dto.setCardIds(account.getCards().stream().map(Cards::getId).collect(Collectors.toSet()));
        }

        return dto;
    }

    public static Accounts toEntity(AccountsDTO dto) {
        Accounts account = new Accounts();
        account.setId(dto.getId());
        account.setIban(dto.getIban());
        account.setBalance(dto.getBalance());
        account.setPaymentLimit(dto.getPaymentLimit());
        return account;
    }
}
