package Commands;

import Exceptions.InsufficientFundsException;

public class BuyStocksCommand extends Command {
    public BuyStocksCommand() {
        super();
    }

    public BuyStocksCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String email = tokens[2];
        String company = tokens[3];
        float noStocks = Float.parseFloat(tokens[4]);

        try {
            appManager.getTransactionService().buyStocks(email, company, noStocks);
        } catch (InsufficientFundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
