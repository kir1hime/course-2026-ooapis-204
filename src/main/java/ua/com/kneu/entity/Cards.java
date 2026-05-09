package ua.com.kneu.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cards")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="hashed_card_number")
    private String hashedCardNumber;
    @Column(name = "expiry_date")
    private String expiryDate;


    @ManyToOne
    @JoinColumn(name = "card_type_id")
    private CardTypes cardType;

    @ManyToMany
    @JoinTable(
            name = "cards_accounts",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private Set<Accounts> accounts = new HashSet<>();

}
