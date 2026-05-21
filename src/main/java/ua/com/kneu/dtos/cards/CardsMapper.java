package ua.com.kneu.dtos.cards;

import ua.com.kneu.entities.*;

import java.util.stream.Collectors;

public class CardsMapper {

    public static CardsDTO toDTO(Cards card) {
        CardsDTO dto = new CardsDTO();
        dto.setId(card.getId());
        dto.setHashedCardNumber(card.getHashedCardNumber());
        dto.setExpiryDate(card.getExpiryDate());

        if (card.getCardType() != null) {
            dto.setCardTypeId(card.getCardType().getId());
            dto.setCardType(card.getCardType().getType());
        }

        if (card.getAccounts() != null) {
            dto.setAccountIds(card.getAccounts().stream().map(Accounts::getId).collect(Collectors.toSet()));
        }

        return dto;
    }

    public static Cards toEntity(CardsDTO dto) {
        Cards card = new Cards();
        card.setId(dto.getId());
        card.setHashedCardNumber(dto.getHashedCardNumber());
        card.setExpiryDate(dto.getExpiryDate());
        return card;
    }
}