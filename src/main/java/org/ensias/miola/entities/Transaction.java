package org.ensias.miola.entities;

import org.ensias.miola.datatypes.Amount;
import org.ensias.miola.datatypes.TransactionType;
import org.ensias.miola.utils.Printer;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType type;
    private Amount amount;
    private double balanceAfterTransaction;
    private LocalDateTime timestamp;

    public Transaction(TransactionType type, Amount amount, LocalDateTime timestamp) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return timestamp;
    }

    public void setDate(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String toString(int amountSpacesNumber) {
        DecimalFormat decimalFormater = new DecimalFormat(Printer.DECIMAL_FORMAT);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Printer.TIME_FORMAT);
        StringBuilder builder = new StringBuilder();

        String stype = (type == TransactionType.DEPOSIT) ? type+" ".repeat(3) : type.toString();
        String stime = timeFormatter.format(timestamp);
        String samount = decimalFormater.format(amount.value);

        builder.append("▶ ");
        builder.append(stype);
        builder.append(" | ");
        builder.append(stime);
        builder.append(" | ");
        builder.append(samount);
        if (amountSpacesNumber > 0) builder.append(" ".repeat(amountSpacesNumber));
        builder.append(" | ");
        builder.append(balanceAfterTransaction);

        return builder.toString();
    }
}
