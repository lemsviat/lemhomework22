package model;

public class Account {
    private Long accountId;
    private Long accountValue;
    private AccountStatus accountStatus;

    public Account() {
    }

    public Account(Long accountValue, AccountStatus accountStatus) {
        this.accountValue = accountValue;
        this.accountStatus = accountStatus;
    }

    public Account(Long accountId, Long accountValue, AccountStatus accountStatus) {
        this.accountId = accountId;
        this.accountValue = accountValue;
        this.accountStatus = accountStatus;
    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(Long accountValue) {
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
