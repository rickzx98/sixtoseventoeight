/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import bank.model.Account;
import bank.model.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author m27
 */
public class AccountService {

    public static void main(String... args) {
        Account account = Account.loadAccount("00001", "Jerico DG", new Date(), 10000.00);
        account.deposit(20000);
        account.debit(199);
        account.debit(135);
        account.debit(1993);
        account.debit(180);
        account.withdraw(599);
        account.debit(8899);
        account.withdraw(899);
        account.debit(199);
        account.deposit(20000);
        account.transfer(20522, "000002");
        account.withdraw(16000);
        account.deposit(20000);
        account.withdraw(2000);
        account.debit(135);
        account.debit(1993);
        account.debit(18);
        account.transfer(5000, "000002");
        List<Transaction> transactions = account.getTransactions();

        // with jdk 7 and below
        List<Transaction> withdrawals = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType().equals(Transaction.TransactionType.WITHDRAW)) {
                withdrawals.add(transaction);
            }
        }

        // get ammount 
        double withdrawalAmount = 0;
        for (Transaction withdraw : withdrawals) {
            withdrawalAmount += withdraw.getTransactionAmount();
        }

        System.out.println(withdrawals);
        System.out.println(withdrawalAmount);
    }
}
