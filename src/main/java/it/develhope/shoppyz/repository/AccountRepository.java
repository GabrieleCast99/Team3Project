package it.develhope.shoppyz.repository;

import it.develhope.shoppyz.entity.Account;

import java.util.List;

public interface AccountRepository {
    public Account getAccount(int id);

    public void deleteAccount(int id);

    public void saveAccount(Account account);

    public void updateAccount(int id);

    public List<Account> getAccounts(List<Account> account);
}