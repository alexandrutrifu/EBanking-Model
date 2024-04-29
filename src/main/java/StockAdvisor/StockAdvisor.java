package StockAdvisor;

import Entities.Stock;

import java.util.*;

public class StockAdvisor {
    public StockAdvisor() {
    }

    /** Recommends stocks based on the simple moving average (SMA) strategy.
     *
     * @return a set of stock names that are recommended for buying.
     */
    public Set<String> recommendStocks() {
        Set<String> recommendedStocks = new HashSet<>();
        Map<String, List<Float>> stockValues = Stock.getStockValues();

        Iterator<Map.Entry<String, List<Float>>> iterator = stockValues.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, List<Float>> entry = iterator.next();

            String stockName = entry.getKey();
            List<Float> values = entry.getValue();

            float[] SMAs = calculateSimpleMovingAverages(values);

            if (SMAs[0] > SMAs[1]) {
                recommendedStocks.add(stockName);
            }
        }

        return recommendedStocks;
    }

    /** Calculates the simple moving averages (SMA) for a given list of stock values.
     *
     * @param values the list of stock values.
     * @return an array of two floats, the first one being the short-term SMA and the second one being the long-term SMA.
     */
    private float[] calculateSimpleMovingAverages(List<Float> values) {
        float[] SMAs = new float[2];
        float longTermSum = 0;

        for (int i = 0; i < values.size(); i++) {
            longTermSum += values.get(i);
        }

        float longTermSMA = longTermSum / values.size();

        float shortTermSum = 0;

        for (int i = values.size() - 5; i < values.size(); i++) {
            shortTermSum += values.get(i);
        }

        float shortTermSMA = shortTermSum / 5;

        SMAs[0] = shortTermSMA;
        SMAs[1] = longTermSMA;

        return SMAs;
    }
}
