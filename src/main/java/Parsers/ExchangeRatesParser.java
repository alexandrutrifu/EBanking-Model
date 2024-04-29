package Parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import App.EBankingApp;
import App.EBankingApp.Currency;

public class ExchangeRatesParser {
    private final File exchangeRatesFile;
    private Map<Currency, Map<Currency, Float>> exchangeRates;

    public ExchangeRatesParser() {
        this(EBankingApp.getInstance().getInputFiles());
    }

    public ExchangeRatesParser(String[] inputFiles) {
        this.exchangeRatesFile = new File(inputFiles[0]);
        exchangeRates = new HashMap<>();
    }

    public Map<Currency, Map<Currency, Float>> parseExchangeRates() {
        try (Scanner scanner = new Scanner(exchangeRatesFile)) {
            // Read the first line, containing the currencies
            String currenciesLine = scanner.nextLine();

            // Split the line into tokens and allocate their respective maps
            String[] currencies = currenciesLine.split(",");
            for (String currency : Arrays.asList(currencies).subList(1, currencies.length)) {   // Skip the first token, which is "Base"
                exchangeRates.put(Currency.valueOf(currency), new HashMap<>());
            }

            // Read the exchange rates
            while (scanner.hasNextLine()) {
                String exchangeRateLine = scanner.nextLine();
                String[] exchangeRateTokens = exchangeRateLine.split(",");

                updateExchangeRates(currencies, exchangeRateTokens);
            }

            return exchangeRates;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private void updateExchangeRates(String[] currencies, String[] exchangeRateTokens) {
        Currency baseCurrency = Currency.valueOf(exchangeRateTokens[0]);
        for (int i = 1; i < exchangeRateTokens.length; ++i) {
            Currency currency = Currency.valueOf(currencies[i]);
            float exchangeRate = Float.parseFloat(exchangeRateTokens[i]);

            exchangeRates.get(baseCurrency).put(currency, exchangeRate);
        }
    }
}