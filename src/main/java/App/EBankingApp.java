package App;

import Entities.Stock;
import Entities.User;
import PersonalActions.PersonalAction;
import PersonalActions.PersonalActionProxy;
import PersonalActions.PersonalActionsInterface;
import Transactions.Transaction;
import Transactions.TransactionInterface;
import Transactions.TransactionProxy;

import java.io.File;
import java.util.*;

public class EBankingApp {  // Singleton
    private static EBankingApp eBankingApp;
    private String[] inputFiles;
    private HashSet<User> users;
    private List<Stock> stocks;
    public enum Currency {
        USD, EUR, GBP, JPY, CAD
    }

    private EBankingApp() {
        users = new HashSet<>();
    }

    public static EBankingApp getInstance() {
        if (eBankingApp == null)
            eBankingApp = new EBankingApp();
        return eBankingApp;
    }

    public void init() {
        stocks = Stock.initializeCurrentPrices();

        AppManager appManager = AppManager.getInstance();

        appManager.solveRequests();
    }

    public void resetLogs() {
        File logFile = new File("./src/main/resources/appLogs/log.txt");

        if (logFile.exists()) {
            logFile.delete();
        }
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setInputFiles(String[] inputFiles) {
        for (int i = 0; i < inputFiles.length; i++) {
            inputFiles[i] = "./src/main/resources/" + inputFiles[i];
        }
        this.inputFiles = inputFiles;
    }

    public String[] getInputFiles() {
        return inputFiles;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
