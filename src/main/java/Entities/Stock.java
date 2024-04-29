package Entities;

import App.EBankingApp;
import Parsers.StockValueParser;

import java.io.Serializable;
import java.util.*;

public class Stock implements Serializable {
    private String stockName;
    private float stockPrice;
    private static Map<String, List<Float>> stockValues;

    public Stock() {
    }

    public Stock(String stockName, float stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    /** Initializes stock prices with the most recent values from the stock values file.
     *
     */
    public static List<Stock> initializeCurrentPrices() {
        List<Stock> stocks = new ArrayList<>();

        StockValueParser stockValueParser = new StockValueParser();

        stockValues = stockValueParser.parseStockValues();

        Iterator<Map.Entry<String, List<Float>>> iterator = stockValues.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, List<Float>> entry = iterator.next();

            String stockName = entry.getKey();
            List<Float> values = entry.getValue();

            stocks.add(new Stock(stockName, values.get(values.size() - 1)));
        }

        return stocks;
    }

    public static Stock getStockByName(String stockName) {
        List<Stock> stocks = EBankingApp.getInstance().getStocks();

        for (Stock stock : stocks) {
            if (stock.getStockName().equals(stockName))
                return stock;
        }

        return null;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public static Map<String, List<Float>> getStockValues() {
        return stockValues;
    }
}
