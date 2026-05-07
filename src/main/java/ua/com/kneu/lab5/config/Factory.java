package ua.com.kneu.lab5.config;

import jakarta.persistence.Persistence;
import org.hibernate.Cache;
import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.dao.*;

public class Factory {
    public final static Factory INSTANCE = new Factory();

    public static Factory getInstance() {
        return INSTANCE;
    }

    private final SessionFactory session;

    public Factory() {
        this.session = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    public AccountDao getAccountDao() {
        return new AccountDao(session);
    }

    public AdminDao getAdminDao() {
        return new AdminDao(session);
    }

    public CardDao getCardDao() {
        return new CardDao(session);
    }

    public CardTypeDao getCardTypeDao() {
        return new CardTypeDao(session);
    }

    public CurrenciesDao getCurrencyDao() {
        return new CurrenciesDao(session);
    }

    public ClientDao getClientDao() {
        return new ClientDao(session);
    }

    public UserDao getUserDao() {
        return new UserDao(session);
    }
}
