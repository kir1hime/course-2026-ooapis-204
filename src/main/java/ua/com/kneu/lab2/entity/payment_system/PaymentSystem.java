package ua.com.kneu.lab2.entity.payment_system;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab2.entity.user.admin.Admin;
import ua.com.kneu.lab2.entity.user.client.Client;

import java.util.List;

public class PaymentSystem {

    private List<Account> accounts;
    private List<Client> clients;
    private List<Admin> admins;

    public PaymentSystem(List<Account> accounts, List<Client> clients, List<Admin> admins) {
        this.accounts = accounts;
        this.clients = clients;
        this.admins = admins;
    }

    public PaymentSystem() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "PaymentSystem{" + "\n\n" +
                "accounts=" + accounts + "\n\n" +
                "clients=" + clients + "\n\n" +
                "admins=" + admins +
                '}';
    }
}
