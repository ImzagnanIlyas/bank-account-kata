package org.ensias.miola.entities;

import org.ensias.miola.datatypes.Amount;
import org.ensias.miola.datatypes.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;

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
}
