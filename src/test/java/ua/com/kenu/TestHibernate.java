package ua.com.kenu;

import org.junit.jupiter.api.*;
import ua.com.kneu.config.Factory;
import ua.com.kneu.dao.*;
import ua.com.kneu.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHibernate {
    private final Factory factory = Factory.getInstance();

    private final AccountDao accountDao = factory.getAccountDao();
    private final AdminDao adminDao = factory.getAdminDao();
    private final AdminTypeDao adminTypeDao = factory.getAdminTypeDao();
    private final CardDao cardDao = factory.getCardDao();
    private final CardTypeDao cardTypeDao = factory.getCardTypeDao();
    private final ClientDao clientDao = factory.getClientDao();
    private final CurrencyDao currencyDao = factory.getCurrencyDao();
    private final UserDao userDao = factory.getUserDao();


    private final Currencies usd = new Currencies(null, "USD");
    private final Currencies uah = new Currencies(null, "UAH");

    private final AdminTypes superAdminType = new AdminTypes(null, "SUPER_ADMIN");

    private final CardTypes debit = new CardTypes(null, "DEBIT");
    private final CardTypes credit = new CardTypes(null, "CREDIT");

    private final Users user1 = new Users(null, "client_ivan", "pass1");
    private final Users user2 = new Users(null, "client_anna", "pass2");
    private final Users userAdmin = new Users(null, "admin_boss", "adminpass");

    private final Clients client1 = new Clients(null, "Ivan", "Ivanov", "ivan@mail.com", "Ivanych", (Integer) 30, "123", "Kyiv", new ArrayList<>(), user1);
    private final Clients client2 = new Clients(null, "Anna", "Petrova", "anna@mail.com", "Sergeevna", (Integer) 25, "456", "Lviv", new ArrayList<>(), user2);

    private final Admins admin = new Admins(null, userAdmin, superAdminType);

    private final Accounts accIvan = new Accounts(null, "IBAN_IVAN_1", new BigDecimal("1000"), new BigDecimal("500"), usd, client1, new HashSet<>());
    private final Accounts accAnna1 = new Accounts(null, "IBAN_ANNA_1", new BigDecimal("5000"), new BigDecimal("1000"), uah, client2, new HashSet<>());
    private final Accounts accAnna2 = new Accounts(null, "IBAN_ANNA_2", new BigDecimal("200"), new BigDecimal("0"), usd, client2, new HashSet<>());


    private final Cards card1 = new Cards(null, "hash_1", "12/26", debit, new HashSet<>(Set.of(accIvan)));
    private final Cards card2 = new Cards(null, "hash_2", "01/27", credit, new HashSet<>(Set.of(accIvan)));
    private final Cards card3 = new Cards(null, "hash_3", "05/25", debit, new HashSet<>(Set.of(accAnna1)));
    private final Cards card4 = new Cards(null, "hash_4", "08/28", credit, new HashSet<>(Set.of(accAnna2)));

    @Test
    @Order(1)
    void saveDataTest() {
        currencyDao.save(usd);
        currencyDao.save(uah);

        adminTypeDao.save(superAdminType);

        cardTypeDao.save(credit);
        cardTypeDao.save(debit);

        userDao.save(user1);
        userDao.save(user2);
        userDao.save(userAdmin);

        clientDao.save(client1);
        clientDao.save(client2);

        accountDao.save(accIvan);
        accountDao.save(accAnna1);
        accountDao.save(accAnna2);

        cardDao.save(card1);
        cardDao.save(card2);
        cardDao.save(card3);
        cardDao.save(card4);

        adminDao.save(admin);
    }

    @Test
    @Order(2)
    void findByIdTest() {
        Currencies c1 = currencyDao.findById(1L);
        assertEquals("USD", c1.getCurrency());

        AdminTypes at1 = adminTypeDao.findById(1L);
        assertEquals("SUPER_ADMIN", at1.getType());

        CardTypes ct2 = cardTypeDao.findById(2L);
        assertEquals("DEBIT", ct2.getType());

        Users u2 = userDao.findById(2L);
        assertEquals((Long) 2L, u2.getId());
        assertEquals("client_anna", u2.getUsername());

        Clients cl2 = clientDao.findById(2L);
        assertEquals((Long) 2L, cl2.getId());
        assertEquals("Anna", cl2.getFirstName());
        assertEquals("Lviv", cl2.getAddress());

        Admins a1 = adminDao.findById(1L);
        assertEquals((Long) 1L, a1.getId());
        assertEquals("admin_boss", a1.getUser().getUsername());
        assertEquals("SUPER_ADMIN", a1.getAdminType().getType());

        Accounts acc2 = accountDao.findById(2L);
        assertEquals((Long) 2L, acc2.getId());
        assertEquals("IBAN_ANNA_1", acc2.getIban());
        assertEquals(new BigDecimal("5000.00"), acc2.getBalance());
        assertEquals("UAH", acc2.getCurrency().getCurrency());

        Cards card4 = cardDao.findById(4L);
        assertEquals((Long) 4L, card4.getId());
        assertEquals("hash_4", card4.getHashedCardNumber());
        assertEquals("08/28", card4.getExpiryDate());
        assertEquals("CREDIT", card4.getCardType().getType());
    }

    @Test
    @Order(3)
    void findAllTest() {
        List<Accounts> accountsList = accountDao.findAll();
        assertEquals(3, accountsList.size());

        List<AdminTypes> adminTypesList = adminTypeDao.findAll();
        assertEquals(1, adminTypesList.size());

        List<Admins> adminsList = adminDao.findAll();
        assertEquals(1, adminsList.size());

        List<CardTypes> cardTypesList = cardTypeDao.findAll();
        assertEquals(2, cardTypesList.size());

        List<Cards> cardsList = cardDao.findAll();
        assertEquals(4, cardsList.size());

        List<Clients> clientsList = clientDao.findAll();
        assertEquals(2, clientsList.size());

        List<Currencies> currenciesList = currencyDao.findAll();
        assertEquals(2, currenciesList.size());

        List<Users> usersList = userDao.findAll();
        assertEquals(3, usersList.size());
    }

    @Test
    @Order(4)
    void updateTest() {
        usd.setCurrency("EUR");
        currencyDao.update(usd);
        Currencies c = currencyDao.findById(usd.getId());
        assertEquals("EUR", c.getCurrency());

        superAdminType.setType("JUST ADMIN");
        adminTypeDao.update(superAdminType);
        AdminTypes ad = adminTypeDao.findById(superAdminType.getId());
        assertEquals("JUST ADMIN", ad.getType());

        debit.setType("PREPAID");
        cardTypeDao.update(debit);
        CardTypes updatedDebit = cardTypeDao.findById(debit.getId());
        assertEquals("PREPAID", updatedDebit.getType());

        user1.setUsername("client_ivan_updated");
        userDao.update(user1);
        Users updatedUser = userDao.findById(user1.getId());
        assertEquals("client_ivan_updated", updatedUser.getUsername());

        client1.setAddress("Odessa");
        clientDao.update(client1);
        Clients updatedClient = clientDao.findById(client1.getId());
        assertEquals("Odessa", updatedClient.getAddress());

        accIvan.setBalance(new BigDecimal("9999"));
        accountDao.update(accIvan);
        Accounts updatedAcc = accountDao.findById(accIvan.getId());
        assertEquals(0, new BigDecimal("9999").compareTo(updatedAcc.getBalance()));

        card1.setExpiryDate("12/30");
        cardDao.update(card1);
        Cards updatedCard = cardDao.findById(card1.getId());
        assertEquals("12/30", updatedCard.getExpiryDate());

        admin.setAdminType(superAdminType);
        adminDao.update(admin);
        Admins updatedAdmin = adminDao.findById(admin.getId());
        assertEquals("JUST ADMIN", updatedAdmin.getAdminType().getType());
    }

    @Test
    @Order(5)
    void deleteTest() {
        cardDao.delete(card1);
        assertEquals(3, cardDao.findAll().size());

        accountDao.delete(accIvan);
        assertEquals(2, accountDao.findAll().size());

        clientDao.delete(client1);
        assertEquals(1, clientDao.findAll().size());

        userDao.delete(user1);
        assertEquals(2, userDao.findAll().size());

        cardTypeDao.delete(credit);
        assertEquals(1, cardTypeDao.findAll().size());

        adminTypeDao.delete(superAdminType);
        assertEquals(0, adminDao.findAll().size());

        currencyDao.delete(usd);
        assertEquals(1, currencyDao.findAll().size());
    }

    @Test
    @Order(6)
    void deleteAllTest() {
        userDao.deleteAll();
        assertEquals(0, userDao.findAll().size());

        currencyDao.deleteAll();
        assertEquals(0, currencyDao.findAll().size());

        clientDao.deleteAll();
        assertEquals(0, clientDao.findAll().size());

        cardDao.deleteAll();
        assertEquals(0, cardDao.findAll().size());

        cardTypeDao.deleteAll();
        assertEquals(0, cardTypeDao.findAll().size());

        accountDao.deleteAll();
        assertEquals(0, accountDao.findAll().size());
    }


}
