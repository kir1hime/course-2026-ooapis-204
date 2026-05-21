package ua.com.kneu.dtos.card_type;

import ua.com.kneu.entities.CardTypes;

public class CardTypesMapper {

    public static CardTypesDTO toDTO(CardTypes cardType) {
        return new CardTypesDTO(cardType.getId(), cardType.getType());
    }

    public static CardTypes toEntity(CardTypesDTO dto) {
        return new CardTypes(dto.getId(), dto.getType());
    }
}
