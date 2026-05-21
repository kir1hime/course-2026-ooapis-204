package ua.com.kneu.dtos.currencies;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrenciesDTO {
    private Long id;
    private String currency;
}