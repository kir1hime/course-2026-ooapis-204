package ua.com.kneu.lab5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String iban;
    private BigDecimal balance;
    @Column(name = "payment_limit")
    private BigDecimal paymentLimit;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currencies currency;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;


    @ManyToMany(mappedBy = "accounts")
    private Set<Cards> cards = new HashSet<>();
}
