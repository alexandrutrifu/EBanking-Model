package Transactions;

import App.EBankingApp.Currency;
import Exceptions.InsufficientFundsException;
import Exceptions.OperationNotAllowedException;
import Parsers.ExchangeRatesParser;

import javax.swing.plaf.ColorUIResource;
import java.util.Map;

public interface TransactionInterface {
    double accountBalanceThreshold = 150;
    void addMoney(String email, Currency currency, float amount);
    void exchangeMoney(String email, Currency sourceCurrency, Currency destinationCurrency, float amount) throws InsufficientFundsException;
    void transferMoney(String email, String friendEmail, Currency currency, float amount) throws InsufficientFundsException, OperationNotAllowedException;
    void buyStocks(String email, String company, float noStocks) throws InsufficientFundsException;

    /** Get the exchange rates from the exchange rates file using the ExchangeRatesParser
     *
     * @return a map of maps, where the first key is the base currency, and the second key is the currency to which the exchange rate is applied
     */
    default Map<Currency, Map<Currency, Float>> getExchangeRates() {
        ExchangeRatesParser exchangeRatesParser = new ExchangeRatesParser();
        return exchangeRatesParser.parseExchangeRates();
    }
}
