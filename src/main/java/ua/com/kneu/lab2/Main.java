package ua.com.kneu.lab2;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab2.entity.account.AccountState;
import ua.com.kneu.lab2.entity.account.Currency;
import ua.com.kneu.lab2.entity.card.Card;
import ua.com.kneu.lab2.entity.card.CardType;
import ua.com.kneu.lab2.entity.payment_system.PaymentSystem;
import ua.com.kneu.lab2.entity.user.admin.Admin;
import ua.com.kneu.lab2.entity.user.admin.AdminType;
import ua.com.kneu.lab2.entity.user.client.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {
        Client client1 = new Client(
                1L,
                "Denys",
                "Shevchenko",
                "Olegovich",
                "den.sheva2000@gmail.com",
                true,
                20,
                "+380000000000",

                "Kyiv");

        Card card1 = new Card(
                11L,
                "#a0aflasdf32",
                "09/28",
                "#08aflalf",
                CardType.DEBIT);

        Card card2 = new Card(
                12L,
                "#0a8f09af0",
                "06/29",
                "#asdf9adsfk",
                CardType.CREDIT);

        Account account1 = new Account(
                111L,
                "UA213996220000026007233566001",
                new BigDecimal(4000),
                new BigDecimal(2500),
                Currency.UAH,
                AccountState.ACTIVE);

        account1.setCard(card1);
        account1.setClient(client1);

        Account account2 = new Account(
                112L,
                "UA903052992990004149123456789",
                new BigDecimal(8000),
                new BigDecimal(100000),
                Currency.UAH,
                AccountState.ACTIVE);
        account2.setCard(card2);

        account2.setClient(client1);

        card1.setAccount(account1);
        card2.setAccount(account2);

        client1.setAccounts(new ArrayList<>(List.of(account1, account2)));

        Client client2 = new Client(
                2L,
                "Ivan",
                "Popeluh",
                "Stepanovich",
                "ivan.popel@gmail.com",
                true,
                24,
                "+380000000000",
                "Kyiv");

        Card card3 = new Card(
                13L,
                "#adsf0adf",
                "11/27",
                "#asdf79asdf",
                CardType.DEBIT);

        Card card4 = new Card(
                14L,
                "#ads098fasdf",
                "02/30",
                "#asdf0lasdf",
                CardType.CREDIT);

        Account account3 = new Account(
                113L,
                "UA573543470006762462054925026",
                new BigDecimal(7000),
                AccountState.ACTIVE,
                new BigDecimal(1000),
                Currency.USD,
                card3,
                client2);

        Account account4 = new Account(
                114L,
                "UA212227320014359034607026807",
                new BigDecimal(800),
                AccountState.ACTIVE,
                new BigDecimal(20),
                Currency.EUR,
                card4,
                client2);

        card3.setAccount(account3);
        card4.setAccount(account4);

        client2.setAccounts(new ArrayList<>(List.of(account3, account4)));

        Admin admin = new Admin(
                1111L,
                "Petro",
                "Kravchenko",
                "admin.admin@gmail.com",
                true,
                "#808Fasd9fasf",
                AdminType.MAIN_ADMIN);


       /* PaymentSystem paymentSystem = new PaymentSystem();
        paymentSystem.setAccounts(new ArrayList<>(List.of(account1, account2, account3, account4)));
        paymentSystem.setClients(new ArrayList<>(List.of(client1, client2)));
        paymentSystem.setAdmins(new ArrayList<>(List.of(admin)));
        System.out.println("Payment system: " + paymentSystem + "\n");

        System.out.println("Client1: " + client1 + "\n");
        System.out.println("Account1: " + account1 + "\n");
        System.out.println("Card1: " + card1 + "\n");
        System.out.println("Admin: " + admin + "\n");

        Account firstAccountOfClient1 = client1.getAccounts().getFirst();

        System.out.println("\n");
        System.out.println("Start state: " + firstAccountOfClient1);
        client1.makePayment(firstAccountOfClient1, new BigDecimal(100));
        System.out.println("After successful payment: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getBalance());
        client1.makePayment(firstAccountOfClient1, new BigDecimal(3000));
        System.out.println("After unsuccessful payment: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getBalance());

        System.out.println("\n");
        System.out.println("Start state: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getBalance());
        client1.topUpAccount(firstAccountOfClient1, new BigDecimal(3000));
        System.out.println("After successful payment: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getBalance());
        client1.topUpAccount(firstAccountOfClient1, new BigDecimal(-1000));
        System.out.println("After unsuccessful payment: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getBalance());

        System.out.println("\n");
        client1.blockAccount(firstAccountOfClient1);
        System.out.println("After successful account blocking: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getAccountState());
        System.out.print("Unsuccessful account blocking: ");
        client1.blockAccount(account4);

        System.out.println("\n");
        admin.activeAccount(firstAccountOfClient1);
        System.out.println("After successful account activation: " + firstAccountOfClient1.getIban() + " " + firstAccountOfClient1.getAccountState());*/
    }
}
