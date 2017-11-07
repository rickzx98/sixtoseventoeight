/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import bank.model.Account;
import bank.model.Person;
import bank.model.Transaction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author m27
 */
public class AccountService {

    public static void main(String... args) {
        List<Account> accounts = new ArrayList<>();
        // accounts.add(createJericAccount());
        // accounts.add(createNicaAccount());
        // accounts.add(createAnaAccount());
        //accounts.add(createMinhoAccount());

        // get account names
        // map uppercase 
        // filter MALE
        // print out result
    }

    /**
     * Stream - forEach - filter - map
     *
     * @return
     */
    static Account createJericAccount() {
        Account account = Account.loadAccount("00001", "Jerico DG", new Date(), Person.Gender.MALE, 10000.00);
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

        List<Double> amounts = new ArrayList<>();

        for (Transaction withdraw : withdrawals) {
            amounts.add(withdraw.getTransactionAmount());
        }

        for (Double amount : amounts) {
            System.out.println("amount: " + amount);
        }

        // refactor using java 8 stream
        return account;
    }

    /**
     * Stream - reduce
     *
     * @return
     */
    static Account createNicaAccount() {
        Account account = Account.loadAccount("00002", "Nica DG", new Date(), Person.Gender.FEMALE, 10000.00);
        account.deposit(200000);
        account.debit(199);
        account.debit(135);
        account.debit(1993);
        account.debit(180);
        account.withdraw(599);
        account.debit(8899);
        account.withdraw(8990);
        account.debit(199);
        account.deposit(20000);
        account.transfer(25220, "000001");
        account.withdraw(160000);
        account.deposit(20000);
        account.withdraw(2000);
        account.debit(135);
        account.debit(1993);
        account.debit(18);
        account.transfer(5000, "000001");

        double totalDebitAmount;
        totalDebitAmount = account.getTransactions().stream()
                .filter(trasaction -> trasaction.getTransactionType().equals(Transaction.TransactionType.DEBIT))
                .map(transaction -> transaction.getTransactionAmount())
                .reduce(0.00, (current, amount) -> current + amount);

        System.out.println("Nica total debit amount: " + totalDebitAmount);

        return account;
    }

    /**
     * Stream -sum - min - max - count
     *
     * @return
     */
    static Account createAnaAccount() {
        Account account = Account.loadAccount("00003", "Ana DG", new Date(), Person.Gender.FEMALE, 10000.00);
        account.deposit(400000);
        account.debit(199);
        account.debit(135);
        account.debit(1993);
        account.debit(180);
        account.withdraw(599);
        account.debit(8899);
        account.withdraw(8990);
        account.debit(199);
        account.deposit(20000);
        account.transfer(25220, "000001");
        account.withdraw(160000);
        account.deposit(20000);
        account.withdraw(2000);
        account.debit(135);
        account.debit(1993);
        account.debit(18);
        account.transfer(5000, "000002");
        account.deposit(20000);

        // get total amount deposit
        List<Transaction> transactions = account.getTransactions();

        double totalDeposit = transactions.stream().filter(transaction -> transaction.getTransactionType().equals(Transaction.TransactionType.DEPOSIT))
                .mapToDouble(Transaction::getTransactionAmount)
                .sum();

        System.out.println(String.format("Total deposit of Ana DG: %f ", totalDeposit));

        double highestDesposit = transactions.stream()
                .filter(transaction -> transaction.getTransactionType().equals(Transaction.TransactionType.DEPOSIT))
                .mapToDouble(Transaction::getTransactionAmount)
                .max().getAsDouble();

        double lowestDesposit = transactions.stream()
                .filter(transaction -> transaction.getTransactionType().equals(Transaction.TransactionType.DEPOSIT))
                .mapToDouble(Transaction::getTransactionAmount)
                .min().getAsDouble();

        return account;
    }

    /**
     * Stream - collect - toList - groupingBy - findFirst
     *
     * @return
     */
    static Account createMinhoAccount() {
        Account account = Account.loadAccount("00004", "Yoo MinHo", new Date(), Person.Gender.MALE, 10000.00);
        account.deposit(4000000);
        account.debit(199);
        account.debit(135);
        account.debit(1993);
        account.debit(180);
        account.debit(8899);
        account.debit(199);
        account.deposit(20000);
        account.transfer(25220, "000001");
        account.deposit(20000);
        account.debit(135);
        account.debit(1993);
        account.debit(18);
        account.transfer(5000, "000002");
        account.deposit(20000);

        List<Transaction> transactions = account.getTransactions();

        // filter all withdrawal transactions
        List<Transaction> withdrawals = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType().equals(Transaction.TransactionType.WITHDRAW)) {
                withdrawals.add(transaction);
            }
        }

        // sorted by highest withdrawal amount
        withdrawals.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o2.getBalance().getAmount().compareTo(o1.getBalance().getAmount());
            }
        });

        if (!withdrawals.isEmpty()) {
            System.out.println("Highest withdrawal amount: " + withdrawals.get(0).getBalance().getAmount().toString());
        }

        // refactor with java 8
        // find first
        // toList
        // group transactions
        Map<Transaction.TransactionType, List<Transaction>> byType = account.getTransactions().stream().
                collect(Collectors.groupingBy(Transaction::getTransactionType));

        return account;
    }
}
