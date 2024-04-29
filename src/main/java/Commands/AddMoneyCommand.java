package Commands;

import App.EBankingApp;

public class AddMoneyCommand extends Command {
    public AddMoneyCommand() {
        super();
    }

    public AddMoneyCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String email = tokens[2];
        EBankingApp.Currency currency = EBankingApp.Currency.valueOf(tokens[3]);
        float amount = Float.parseFloat(tokens[4]);

        appManager.getTransactionService().addMoney(email, currency, amount);
    }
}
