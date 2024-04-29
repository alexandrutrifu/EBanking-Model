package Parsers;

import App.EBankingApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StockValueParser {
    private final File stockValuesFile;
    private final Map<String, List<Float>> stockValues;

    public StockValueParser() {
        stockValuesFile = new File(EBankingApp.getInstance().getInputFiles()[1]);
        stockValues = new HashMap<>();
    }

    public Map<String, List<Float>> parseStockValues() {
        try (Scanner scanner = new Scanner(stockValuesFile)) {
            // Read the first line, containing the dates (we don't use them)
            String datesLine = scanner.nextLine();

            while (scanner.hasNextLine()) {
                String stockValueLine = scanner.nextLine();
                String[] stockValueTokens = stockValueLine.split(",");

                updateStockValues(stockValueTokens);
            }

            return stockValues;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private void updateStockValues(String[] stockValueTokens) {
        String stockName = stockValueTokens[0];
        List<Float> values = new ArrayList<>();

        for (int i = 1; i < stockValueTokens.length; i++) {
            values.add(Float.parseFloat(stockValueTokens[i]));
        }

        this.stockValues.put(stockName, values);
    }
}
