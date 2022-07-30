package org.ensias.miola.app;

import org.ensias.miola.datatypes.AccountNumber;
import org.ensias.miola.datatypes.Amount;
import org.ensias.miola.datatypes.Colors;
import org.ensias.miola.datatypes.Pin;
import org.ensias.miola.entities.Account;
import org.ensias.miola.entities.Transaction;
import org.ensias.miola.services.AuthService;
import org.ensias.miola.services.BankService;
import org.ensias.miola.utils.LocalDatabase;
import org.ensias.miola.utils.Printer;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class BankApplication {
    AuthService authService;
    BankService bankService;

    public BankApplication() {
        authService = new AuthService();
        bankService = new BankService();
    }

    public void init(){
        LocalDatabase.accounts.add(new Account(new AccountNumber("12345678"), new Pin("1234")));
    }

    public void start(){
        init();
        authentication();
        menu();
    }

    private void authentication(){
        Scanner scanner = new Scanner(System.in);
        PrintStream printer = System.out;
        Printer.printlnLine(Colors.YELLOW);
        Printer.printlnc("Welcome to ENSIAS Bank", Colors.YELLOW);
        Printer.printlnLine(Colors.YELLOW);
        Printer.printTitle("Authentication");
        boolean state = false;
        do {
            System.out.print("Account Number : ");
            String snumber = scanner.nextLine();
            System.out.print("PIN Code : ");
            String spin = scanner.nextLine();
            AccountNumber number;
            Pin pin;
            try{
                number = new AccountNumber(snumber);
                pin = new Pin(spin);
            }catch (RuntimeException e){
                Printer.printlnc(e.getMessage(), Colors.RED);
                continue;
            }
            try{
                boolean authenticationState =  authService.authenticate(number, pin);
                if (!authenticationState) throw new RuntimeException();
                state = true;
                Printer.printlnc("Successful authentication", Colors.GREEN);
            }catch(Exception e){
                Printer.printlnc("Incorrect data", Colors.RED);
            }
        }while (!state);
    }

    private void menu(){
        String title = "Menu";
        String[] options = {
            "1- See account balance",
            "2- Make a deposit",
            "3- Make a withdrawal",
            "4- See transactions history",
            "0- Exit",
        };
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option!=0){
            Printer.printMenu(title, options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        showBalance(); backToMenu(); break;
                    case 2:
                        makeDeposit(); break;
                    case 3:
                        makeWithdrawal(); break;
                    case 4:
                        showTransactionsHistory(); backToMenu(); break;
                    case 0:
                        exit(0);
                    default: Printer.printlnc("Please enter an integer value between 0 and 4", Colors.RED);
                }
            }catch (Exception e){
                Printer.printlnc("Please enter an integer value between 0 and 4", Colors.RED);
                scanner.nextLine();
            }
        }
    }

    private void showBalance(){
        String balance = Printer.DECIMAL_FORMATTER.format(AuthService.authenticatedAccount.getBalance());
        Printer.printTitle("Account Balance : "+balance);

    }

    private void makeDeposit(){
        Scanner scanner = new Scanner(System.in);
        double value;

        Printer.printTitle("Make a DEPOSIT");
        System.out.print("Amount : ");
        value = scanner.nextDouble();

        try{
            Amount amount = new Amount(value);
            boolean state = bankService.deposit(amount);
            if (state) Printer.printlnc("Transaction completed successfully", Colors.GREEN);
            else Printer.printlnc("An error has occurred try later", Colors.RED);
        }catch (RuntimeException e){
            Printer.printlnc(e.getMessage(), Colors.RED);
        }
    }

    private void makeWithdrawal(){
        Scanner scanner = new Scanner(System.in);
        double value;

        Printer.printTitle("Make a WITHDRAWAL");
        System.out.print("Amount : ");
        value = scanner.nextDouble();

        try{
            Amount amount = new Amount(value);
            boolean state = bankService.withdrawal(amount);
            if (state) Printer.printlnc("Transaction completed successfully", Colors.GREEN);
            else Printer.printlnc("Your balance is less than the requested amount", Colors.RED);
        }catch (RuntimeException e){
            Printer.printlnc(e.getMessage(), Colors.RED);
        }
    }

    private void showTransactionsHistory(){
        Printer.printTitle("Transactions History");
        List<Transaction> transactions = bankService.getHistory();
        if (transactions.size() > 0)
            Printer.printTransactionsHistory(transactions);
        else
            Printer.printlnc("Empty", Colors.BLACK);
    }

    private void backToMenu(){
        Scanner scanner = new Scanner(System.in);
        Printer.printlnc("Click Enter key to return", Colors.PURPLE);
        scanner.nextLine();
    }
}
