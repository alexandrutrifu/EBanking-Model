package Commands;

import App.EBankingApp;
import Exceptions.InsufficientFundsException;

public class ExchangeMoneyCommand extends Command {
    public ExchangeMoneyCommand() {
        super();
    }

    public ExchangeMoneyCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String email = tokens[2];
        EBankingApp.Currency sourceCurrency = EBankingApp.Currency.valueOf(tokens[3]);
        EBankingApp.Currency destinationCurrency = EBankingApp.Currency.valueOf(tokens[4]);
        float amount = Float.parseFloat(tokens[5]);

        try {
            appManager.getTransactionService().exchangeMoney(email, sourceCurrency, destinationCurrency, amount);
        } catch (InsufficientFundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
