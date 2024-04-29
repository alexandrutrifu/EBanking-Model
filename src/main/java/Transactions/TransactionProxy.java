package Transactions;

import App.EBankingApp;
import App.Proxy;
import Entities.Stock;
import Entities.User;
import Exceptions.InsufficientFundsException;
import Exceptions.OperationNotAllowedException;

/** Proxy class for Transaction
 * <p>
 * It implements the same interface as the real service, but adds extra functionalities. These include: checking for corner cases, and logging.
 */
public class TransactionProxy implements TransactionInterface, Proxy {
    private TransactionInterface realService;

    public TransactionProxy() {
    }

    public TransactionProxy(TransactionInterface realService) {
        this.realService = realService;
    }

    /** Checks if the user exists and has access to the given currency
     *
     * @param user the user to check
     * @param currency the currency to check
     * @throws OperationNotAllowedException
     */
    private void checkAccess(User user, EBankingApp.Currency currency) throws OperationNotAllowedException {
        if (user == null) {
            logFailure("Anonymous user", "Could not fulfill nonexistent user's request");
            throw new OperationNotAllowedException("Could not add money to user's account");
        }
        if (!user.getAccounts().containsKey(currency)) {
            logFailure(user.getEmail(), "User does not have access to " + currency.toString());
            throw new OperationNotAllowedException("Could not add money to user's account");
        }
    }


    @Override
    public void addMoney(String email, EBankingApp.Currency currency, float amount) {
        try {
            checkAccess(User.getUserByEmail(email), currency);
        } catch (OperationNotAllowedException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        realService.addMoney(email, currency, amount);
        logSuccess(email, "ADD MONEY: " + amount + " " + currency.toString());
    }

    @Override
    public void exchangeMoney(String email, EBankingApp.Currency sourceCurrency, EBankingApp.Currency destinationCurrency, float amount) throws InsufficientFundsException {
        try {
            checkAccess(User.getUserByEmail(email), sourceCurrency);
        } catch (OperationNotAllowedException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        // Check if user has enough money in source currency
        if (User.getUserByEmail(email).getAccounts().get(sourceCurrency).getAmount() < amount) {
            throw new InsufficientFundsException("Insufficient amount in account " + sourceCurrency.toString() + " to exchange");
        }

        realService.exchangeMoney(email, sourceCurrency, destinationCurrency, amount);
        logSuccess(email, "EXCHANGE MONEY: " + amount + " " + sourceCurrency.toString() + " to " + destinationCurrency.toString());
    }

    @Override
    public void transferMoney(String email, String friendEmail, EBankingApp.Currency currency, float amount) throws InsufficientFundsException, OperationNotAllowedException {
        try {
            checkAccess(User.getUserByEmail(email), currency);
        } catch (OperationNotAllowedException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        // Check if users are friends
        if (User.getUserByEmail(email).findFriend(User.getUserByEmail(friendEmail)) == null) {
            logFailure(email, "Could not transfer money to non-friend " + friendEmail);
            throw new OperationNotAllowedException("You are not allowed to transfer money to " + friendEmail);
        }

        // Check if user has enough money in source currency
        if (User.getUserByEmail(email).getAccounts().get(currency).getAmount() < amount) {
            logFailure(email, "Insufficient amount in account " + currency.toString() + " to transfer");
            throw new InsufficientFundsException("Insufficient amount in account " + currency.toString() + " for transfer");
        }

        realService.transferMoney(email, friendEmail, currency, amount);
        logSuccess(email, "TRANSFER MONEY: " + amount + " " + currency.toString() + " to " + friendEmail);
    }

    @Override
    public void buyStocks(String email, String company, float noStocks) throws InsufficientFundsException {
        try {
            checkAccess(User.getUserByEmail(email), EBankingApp.Currency.USD);
        } catch (OperationNotAllowedException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        if (noStocks < 0) {
            logFailure(email, "Could not buy negative amount of stocks");
            return;
        }

        // Check if user has enough money in USD
        float totalCost = Stock.getStockByName(company).getStockPrice() * noStocks;
        float userBalance = User.getUserByEmail(email).getAccounts().get(EBankingApp.Currency.USD).getAmount();

        if (userBalance < totalCost) {
            logFailure(email, "Insufficient amount in account USD to buy stocks");
            throw new InsufficientFundsException("Insufficient amount in account for buying stocks");
        }

        realService.buyStocks(email, company, noStocks);
        logSuccess(email, "BOUGHT STOCKS: " + noStocks + " " + company +
                " at a price of " + Stock.getStockByName(company).getStockPrice() + " USD");
    }
}
