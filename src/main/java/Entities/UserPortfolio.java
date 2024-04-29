package Entities;

import App.EBankingApp;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserPortfolio implements Serializable {
    @Expose
    private Map<String, Float> stocks;
    @Expose
    private Map<EBankingApp.Currency, Account> accounts;

    public UserPortfolio() {
        accounts = new HashMap<>();
        stocks = new HashMap<>();
    }

    public Map<EBankingApp.Currency, Account> getAccounts() {
        return accounts;
    }

    public Map<String, Float> getStocks() {
        return stocks;
    }
}
