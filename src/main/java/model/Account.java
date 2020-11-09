package model;

public class Account {
    private int accountValue;
    private AccountStatus accountStatus;

    public Account() {
    }

    public Account(int accountValue, AccountStatus accountStatus) {
        this.accountValue = accountValue;
        this.accountStatus = accountStatus;
    }

    public int getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(int accountValue) {
        this.accountValue = accountValue;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account[" +
                "accountValue=" + accountValue +
                ", accountStatus=" + accountStatus +
                ']';
    }
}
