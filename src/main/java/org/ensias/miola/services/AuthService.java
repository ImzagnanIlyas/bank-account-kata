package org.ensias.miola.services;

import org.ensias.miola.datatypes.AccountNumber;
import org.ensias.miola.datatypes.Pin;
import org.ensias.miola.entities.Account;
import org.ensias.miola.utils.AppUtils;
import org.ensias.miola.utils.LocalDatabase;

public class AuthService {
    public static Account authenticatedAccount;

    public boolean authenticate(AccountNumber number, Pin pin){
        int accountIndex = getAccountIndex(number,pin);
        return saveAuthenticatedAccount(accountIndex);
    }

    public int getAccountIndex(AccountNumber number, Pin pin){
        Account account = new Account(number, pin);
        return LocalDatabase.accounts.indexOf(account);
    }

    public boolean saveAuthenticatedAccount(int accountIndex){
        if (accountIndex > -1){
            authenticatedAccount = LocalDatabase.accounts.get(accountIndex);
            return true;
        }
        return false;
    }
}