package ua.com.kenu;

import org.junit.jupiter.api.Test;
import ua.com.kneu.lab5.config.Factory;
import ua.com.kneu.lab5.dao.AccountDao;
import ua.com.kneu.lab5.dao.CardTypeDao;
import ua.com.kneu.lab5.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestHibernate {
    private final Factory factory = Factory.getInstance();


    @Test
    void test1() {
        AccountDao accountDao = factory.getAccountDao();
        Currencies usd = new Currencies(null, "USD");
        Currencies uah = new Currencies(null, "UAH");

        AdminTypes superAdminType = new AdminTypes(null, "SUPER_ADMIN");

        CardTypes debit = new CardTypes(null, "DEBIT");

        Users user1 = new Users(null, "client_ivan", "pass1");
        Users user2 = new Users(null, "client_anna", "pass2");
        Users userAdmin = new Users(null, "admin_boss", "adminpass");

        Clients client1 = new Clients(null, "Ivan", "Ivanov", "ivan@mail.com", "Ivanych", 30, "123", "Kyiv", new ArrayList<>(), user1);
        Clients client2 = new Clients(null, "Anna", "Petrova", "anna@mail.com", "Sergeevna", 25, "456", "Lviv", new ArrayList<>(), user2);

        Admins admin = new Admins(null, userAdmin, superAdminType);

        Accounts accIvan = new Accounts(null, "IBAN_IVAN_1", new BigDecimal("1000"), new BigDecimal("500"), usd, client1, new HashSet<>());
        Accounts accAnna1 = new Accounts(null, "IBAN_ANNA_1", new BigDecimal("5000"), new BigDecimal("1000"), uah, client2, new HashSet<>());
        Accounts accAnna2 = new Accounts(null, "IBAN_ANNA_2", new BigDecimal("200"), new BigDecimal("0"), usd, client2, new HashSet<>());


        Cards card1 = new Cards(null, "hash_1", "12/26", debit, new HashSet<>(Set.of(accIvan)));
        Cards card2 = new Cards(null, "hash_2", "01/27", debit, new HashSet<>(Set.of(accIvan)));
        Cards card3 = new Cards(null, "hash_3", "05/25", debit, new HashSet<>(Set.of(accAnna1)));
        Cards card4 = new Cards(null, "hash_4", "08/28", debit, new HashSet<>(Set.of(accAnna2)));

        CardTypeDao cardTypeDao = factory.getCardTypeDao();
        cardTypeDao.save(debit);
    }
}
