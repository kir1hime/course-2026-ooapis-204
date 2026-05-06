package ua.com.kneu.lab5.config;

import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;

public class Factory {
    public final static Factory INSTANCE = new Factory();

    public static Factory getInstance() {
        return INSTANCE;
    }

    private final SessionFactory session;

    public Factory() {
        this.session = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }
}
