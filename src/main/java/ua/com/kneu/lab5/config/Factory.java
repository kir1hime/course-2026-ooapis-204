package ua.com.kneu.lab5.config;

import jakarta.persistence.Persistence;
import lombok.Getter;
import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.dao.*;

@Getter
public class Factory {
    public final static Factory INSTANCE = new Factory();

    public static Factory getInstance() {
        return INSTANCE;
    }

    private final SessionFactory session;

    private final AccountDao accountDao;
    private final AdminDao adminDao;
    private final AdminTypeDao adminTypeDao;
    private final CardDao cardDao;
    private final CardTypeDao cardTypeDao;
    private final ClientDao clientDao;
    private final CurrencyDao currencyDao;
    private final UserDao userDao;

    private Factory() {
        this.session = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

        this.accountDao = new AccountDao(session);
        this.adminDao = new AdminDao(session);
        this.adminTypeDao = new AdminTypeDao(session);
        this.cardDao = new CardDao(session);
        this.cardTypeDao = new CardTypeDao(session);
        this.clientDao = new ClientDao(session);
        this.currencyDao = new CurrencyDao(session);
        this.userDao = new UserDao(session);
    }
}
