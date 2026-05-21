package ua.com.kneu.dtos.currencies;

import ua.com.kneu.entities.Currencies;

public class CurrenciesMapper {

    public static CurrenciesDTO toDTO(Currencies currency) {
        return new CurrenciesDTO(currency.getId(), currency.getCurrency());
    }

    public static Currencies toEntity(CurrenciesDTO dto) {
        return new Currencies(dto.getId(), dto.getCurrency());
    }
}