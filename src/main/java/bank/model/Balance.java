/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

/**
 *
 * @author m27
 */
public final class Balance {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public Balance addFund(double amount) {
        double newAmount = this.amount + amount;
        return new Balance(newAmount);
    }

    public Balance reduceFund(double amount) {
        Balance newBalance = null;
        if (this.amount > amount) {
            double newAmount = this.amount - amount;
            newBalance = new Balance(newAmount);
        } else {
            throw new InsuficientFundsException(amount);
        }
        return newBalance;
    }

    public static class InsuficientFundsException extends RuntimeException {

        public InsuficientFundsException(double amount) {
            super(String.format("Unable to deduct %f amount", amount));
        }
    }

    private Balance(double amount) {
        this.amount = amount;
    }

    public static Balance createBalanceWithAmount(double amount) {
        return new Balance(amount);
    }

    public Balance cloneBalance() {
        return new Balance(amount);
    }
}
