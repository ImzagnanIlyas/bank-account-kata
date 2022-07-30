package org.ensias.miola;

import org.ensias.miola.app.BankApplication;
import org.ensias.miola.datatypes.Colors;
import org.ensias.miola.datatypes.Pin;
import org.ensias.miola.datatypes.TransactionType;
import org.ensias.miola.services.BankService;
import org.ensias.miola.utils.Printer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        BankApplication application = new BankApplication();
        application.start();
    }
}