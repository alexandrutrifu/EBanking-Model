package Commands;

import App.EBankingApp;
import Exceptions.InsufficientFundsException;
import Exceptions.OperationNotAllowedException;

public class TransferMoneyCommand extends Command {
    public TransferMoneyCommand() {
        super();
    }

    public TransferMoneyCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String senderEmail = tokens[2];
        String receiverEmail = tokens[3];
        EBankingApp.Currency currency = EBankingApp.Currency.valueOf(tokens[4]);
        float amount = Float.parseFloat(tokens[5]);

        try {
            appManager.getTransactionService().transferMoney(senderEmail, receiverEmail, currency, amount);
        } catch (InsufficientFundsException | OperationNotAllowedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
