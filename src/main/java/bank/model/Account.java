/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author m27
 */
public class Account {

    private String accountNumber;
    private Person accountOwner;
    private List<Transaction> transactions;
    private Balance balance;

    private void transact(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        this.balance = transaction.getBalance().cloneBalance();
        transactions.add(transaction);
    }

    public void withdraw(double amount) {
        transact(Transaction.withdraw(balance, amount));
    }

    public void deposit(double amount) {
        transact(Transaction.deposit(balance, amount));
    }

    public void transfer(double amount, String accountNumber) {
        transact(Transaction.transfer(balance, amount));
    }

    public void debit(double amount) {
        transact(Transaction.debit(balance, amount));
    }

    private Account(String accountNumber, Person accountOwner, Balance balance) {
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.balance = balance;
    }

    public static Account loadAccount(String accountNumber, String fullname, Date birthDate, double balance) {
        return new Account(accountNumber, new Person(fullname, birthDate), Balance.createBalanceWithAmount(balance));
    }

    public Balance getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Person getAccountOwner() {
        return accountOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
