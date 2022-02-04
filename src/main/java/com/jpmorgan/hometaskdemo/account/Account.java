package com.jpmorgan.hometaskdemo.account;

import java.awt.*;
import java.util.List;

public class Account {
    private String accountId;
    private List<String> source;

    public Account() {
    }
    public Account(String accountId) {
        this.accountId = accountId;
    }
    public Account(String accountId, List<String> source) {
        this.accountId = accountId;
        this.source = source;
    }
    public List<String> getSource() {
        return source;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setSource(List<String> source) {
        this.source = source;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", source=" + source +
                '}';
    }
}
