package org.ensias.miola.entities;

import org.ensias.miola.datatypes.AccountNumber;
import org.ensias.miola.datatypes.Amount;
import org.ensias.miola.datatypes.Pin;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String ownerName;
    private AccountNumber number;
    private Pin pin;
    private double balance;
    private List<Transaction> transactions;

    public Account(AccountNumber number, Pin pin) {
        this.number = number;
        this.pin = pin;
        this.balance = 0.0;
        transactions = new ArrayList<>();
    }

    public Account(AccountNumber number, Pin pin, double balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    public void deposit(Amount amount){
        balance += amount.value;
    }

    public void withdrawal(Amount amount){
        balance -= amount.value;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public boolean isWithdrawalAllowed(Amount amount){
        return amount.value <= balance ;
    }

    public boolean executeDepositTransaction(Transaction transaction){
        deposit(transaction.getAmount());
        transaction.setBalanceAfterTransaction(balance);
        addTransaction(transaction);
        return true;
    }

    public boolean executeWithdrawalTransaction(Transaction transaction){
        if ( isWithdrawalAllowed(transaction.getAmount()) ) {
            withdrawal(transaction.getAmount());
            transaction.setBalanceAfterTransaction(balance);
            addTransaction(transaction);
            return true;
        }
        return false;
    }

    public String getNumber() {
        return number.value;
    }

    public void setNumber(AccountNumber number) {
        this.number = number;
    }

    public String  getPin() {
        return pin.value;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (!(obj instanceof Account)) return false;

        Account other = (Account) obj;
        return number.equals(other.number) && pin.equals(other.pin);
    }
}
