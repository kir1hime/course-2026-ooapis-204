package ua.com.kneu.lab2.entity.user.client;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab2.entity.account.AccountState;
import ua.com.kneu.lab2.entity.user.User;

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

    public void makePayment(Account account, BigDecimal amountOfMoney) {
        if (isNotAccountCorrect(account)) {
            return;
        }
        if (isAccountBlocked(account)) {
            return;
        }
        if (amountOfMoney.compareTo(account.getBalance()) > 0) {
            System.out.println("There aren't enough money in your account");
            return;
        }
        if (!isAmountOfMoneyCorrect(amountOfMoney)) {
            return;
        }
        if (amountOfMoney.compareTo(account.getPaymentLimit()) > 0) {
            System.out.println("The amount of money you want to pay is bigger than your limit");
            return;
        }
        account.setBalance(account.getBalance().subtract(amountOfMoney));
    }

    public void blockAccount(Account account) {
        if (isNotAccountCorrect(account)) {
            return;
        }

        account.setAccountState(AccountState.BLOCKED);
    }

    public void topUpAccount(Account account, BigDecimal amountOfMoney) {
        if (isNotAccountCorrect(account)) {
            return;
        }
        if (isAccountBlocked(account)) {
            return;
        }
        if (!isAmountOfMoneyCorrect(amountOfMoney)) {
            return;
        }
        account.setBalance(account.getBalance().add(amountOfMoney));
    }


    private boolean isNotAccountCorrect(Account account) {
        if (!accounts.contains(account)) {
            System.out.println("Incorrect account");
            return true;
        }
        return false;
    }

    private boolean isAccountBlocked(Account account) {
        if (account.getAccountState() == AccountState.BLOCKED) {
            System.out.println("Account is blocked");
            return true;
        }
        return false;
    }

    private boolean isAmountOfMoneyCorrect(BigDecimal amountOfMoney) {
        if (amountOfMoney.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }
        return true;
    }
}
