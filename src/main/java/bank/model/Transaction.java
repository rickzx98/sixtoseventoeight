/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

import java.util.Date;

/**
 *
 * @author m27
 */
public class Transaction {

    public static enum TransactionType {
        DEBIT, WITHDRAW, DEPOSIT, TRANSFER
    }

    private final Balance balance;
    private final double transactionAmount;
    private final TransactionType transactionType;
    private final Date transactionDate;

    private Transaction(Balance balance, double transactionAmount, TransactionType transactionType) {
        this.balance = balance;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDate = new Date();
        System.out.println(String.format("Created new transaction: %s", transactionType.toString()));
        System.out.println(String.format("Remaining balance: %f", balance.getAmount()));
    }

    public static Transaction debit(Balance balance, double transactionAmount) {
        System.out.println(String.format("Amount to debit %f", transactionAmount));
        return new Transaction(balance.reduceFund(transactionAmount), transactionAmount, TransactionType.DEBIT);
    }

    public static Transaction deposit(Balance balance, double transactionAmount) {
        System.out.println(String.format("Amount to deposit %f", transactionAmount));
        return new Transaction(balance.addFund(transactionAmount), transactionAmount, TransactionType.DEPOSIT);
    }

    public static Transaction withdraw(Balance balance, double transactionAmount) {
        System.out.println(String.format("Amount to withdraw %f", transactionAmount));
        return new Transaction(balance.reduceFund(transactionAmount), transactionAmount, Transaction.TransactionType.WITHDRAW);
    }

    public static Transaction transfer(Balance balance, double transactionAmount) {
        System.out.println(String.format("Amount to transfer %f", transactionAmount));
        return new Transaction(balance.reduceFund(transactionAmount), transactionAmount, Transaction.TransactionType.TRANSFER);
    }

    public Balance getBalance() {
        return balance;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

   
}
