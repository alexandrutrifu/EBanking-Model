package Transactions;

import App.EBankingApp;
import App.EBankingApp.Currency;
import Entities.Stock;
import Entities.User;

import java.util.List;

public class Transaction implements TransactionInterface {
    public Transaction() {
    }

    @Override
    public void addMoney(String email, Currency currency, float amount) {
        User.getUserByEmail(email).addMoney(currency, amount);

        notifyUser(User.getUserByEmail(email), "Successfully added " + amount + " " + currency.toString() + " to corresponding account");
    }

    @Override
    public void exchangeMoney(String email, Currency sourceCurrency, Currency destinationCurrency, float amount) {
        User user = User.getUserByEmail(email);

        // Get corresponding exchange rate
        float exchangeRate = getExchangeRates().get(sourceCurrency).get(destinationCurrency);

        // Commission
        if (amount > user.getAccounts().get(sourceCurrency).getAmount() / 2)
            user.subtractMoney(sourceCurrency, 0.01f * amount);

        // Convert amount to destination currency
        float convertedAmount = amount * exchangeRate;

        // Add converted amount to user's account
        user.addMoney(destinationCurrency, convertedAmount);

        // Subtract amount from user's account
        user.subtractMoney(sourceCurrency, amount);

        checkLowBalance(user, sourceCurrency);

        notifyUser(user, "Successfully exchanged " + amount + " " + sourceCurrency.toString() +
                " to " + convertedAmount + " " + destinationCurrency.toString());
    }

    @Override
    public void transferMoney(String email, String friendEmail, Currency currency, float amount) {
        User user = User.getUserByEmail(email);
        User friend = User.getUserByEmail(friendEmail);

        // Add money to friend's account
        friend.addMoney(currency, amount);

        // Subtract money from user's account
        user.subtractMoney(currency, amount);

        checkLowBalance(user, currency);

        notifyUser(user, "Successfully transferred " + amount + " " + currency.toString() +
                " to " + friendEmail);
        notifyUser(friend, "Hooray! Received " + amount + " " + currency.toString() +
                " from " + email);
    }

    @Override
    public void buyStocks(String email, String company, float noStocks) {
        User user = User.getUserByEmail(email);

        // Get stock value
        float stockValue = Stock.getStockByName(company).getStockPrice();

        // Calculate total price in USD
        float totalPrice = stockValue * noStocks;

        // Buy stocks
        user.subtractMoney(Currency.USD, totalPrice);

        checkLowBalance(user, Currency.USD);

        // Add stocks to user's account
        user.getStocks().put(company, user.getStocks().get(company) + noStocks);

        notifyUser(user, "Successfully bought " + noStocks + " stocks from " + company);
    }

    private void notifyUser(User user, String message) {
        user.updateTransactionHistory(message);
    }

    private void checkLowBalance(User user, Currency currency) {
        // Check account balance
        if (user.getAccounts().get(currency).getAmount() < accountBalanceThreshold) {
            notifyUser(user, "Slow down! Account balance is below threshold of " +
                    accountBalanceThreshold + " " + currency.toString());
        }
    }
}
