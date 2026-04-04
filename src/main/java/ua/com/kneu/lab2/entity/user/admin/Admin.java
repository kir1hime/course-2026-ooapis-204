package ua.com.kneu.lab2.entity.user.admin;

import ua.com.kneu.lab2.entity.account.Account;
import ua.com.kneu.lab2.entity.account.AccountState;
import ua.com.kneu.lab2.entity.user.User;

public class Admin extends User {
    private String hashedPassword;
    private AdminType adminType;

    public Admin(long id, String firstName, String lastName, String email, boolean isActive, String hashedPassword, AdminType adminType) {
        super(id, firstName, lastName, email, isActive);
        this.hashedPassword = hashedPassword;
        this.adminType = adminType;
    }

    public Admin() {

    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public AdminType getAdminType() {
        return adminType;
    }

    public void setAdminType(AdminType adminType) {
        this.adminType = adminType;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "hashedPassword='" + hashedPassword + '\'' +
                ", adminType=" + adminType +
                "} ";
    }

    public void activeAccount(Account account) {
        account.setAccountState(AccountState.ACTIVE);
    }
}
