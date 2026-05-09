package ua.com.kneu.entity.user.client;

import ua.com.kneu.entity.account.Account;
import ua.com.kneu.entity.user.User;
import ua.com.kneu.entity.account.account_state.BlockedAccountState;
import ua.com.kneu.exceptions.AccountOwnershipException;
import ua.com.kneu.exceptions.PaymentException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String patronymic;
    private int age;
    private String phone;
    private String address;

    private List<Account> accounts = new ArrayList<>();


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public Client(long id, String firstName, String lastName, String patronymic, String email, boolean isActive, int age, String phone, String address, List<Account> accounts) {
        super(id, firstName, lastName, email, isActive);
        this.patronymic = patronymic;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.accounts = accounts;
    }

    public Client(long id, String firstName, String lastName, String patronymic, String email, boolean isActive, int age, String phone, String address) {
        super(id, firstName, lastName, email, isActive);
        this.patronymic = patronymic;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    public Client() {
    }


    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                super.toString() +
                "patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                "} ";
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void makePayment(Account account, BigDecimal amountOfMoney) throws PaymentException, AccountOwnershipException {
        checkAccount(account);
        account.makePayment(amountOfMoney);
    }

    public void blockAccount(Account account) throws AccountOwnershipException {
        checkAccount(account);
        account.setAccountState(new BlockedAccountState());
    }

    public void topUpAccount(Account account, BigDecimal amountOfMoney) throws PaymentException, AccountOwnershipException {
        checkAccount(account);
        account.topUp(amountOfMoney);
    }

    private void checkAccount(Account account) throws AccountOwnershipException {
        if (!accounts.contains(account)) {
            throw new AccountOwnershipException();
        }
    }
}
