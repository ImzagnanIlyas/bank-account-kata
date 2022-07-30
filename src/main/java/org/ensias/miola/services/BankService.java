package org.ensias.miola.services;

import org.ensias.miola.datatypes.Amount;
import org.ensias.miola.datatypes.TransactionType;
import org.ensias.miola.entities.Account;
import org.ensias.miola.entities.Transaction;
import org.ensias.miola.utils.Printer;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankService {

    public boolean deposit(Amount amount){
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now());
        return AuthService.authenticatedAccount.executeDepositTransaction(transaction);
    }

    public boolean withdrawal(Amount amount){
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, LocalDateTime.now());
        return AuthService.authenticatedAccount.executeWithdrawalTransaction(transaction);
    }

    public List<Transaction> getHistory(){
        return AuthService.authenticatedAccount.getTransactions();
    }

}
