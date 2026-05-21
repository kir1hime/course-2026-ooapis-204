package ua.com.kneu.dtos.cards;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardsDTO {
    private Long id;
    private String hashedCardNumber;
    private String expiryDate;
    private Long cardTypeId;
    private String cardType;
    private Set<Long> accountIds;
}