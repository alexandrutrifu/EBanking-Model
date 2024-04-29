package Commands;

import EntityLister.EntityLister;
import StockAdvisor.StockAdvisor;

public class RecommendStocksCommand extends Command {
    public RecommendStocksCommand() {
        super();
    }

    public RecommendStocksCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        StockAdvisor stockAdvisor = new StockAdvisor();
        EntityLister entityLister = appManager.getEntityLister();

        entityLister.listRecommendedStocks(stockAdvisor.recommendStocks());
    }
}
