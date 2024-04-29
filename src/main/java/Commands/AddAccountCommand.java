package Commands;

import App.EBankingApp;
import Exceptions.AccountAlreadyExistsException;
import Exceptions.UserNotRegisteredException;

public class AddAccountCommand extends Command {
    public AddAccountCommand() {
        super();
    }

    public AddAccountCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String email = tokens[2];
        EBankingApp.Currency currency = EBankingApp.Currency.valueOf(tokens[3]);

        try {
            appManager.getPersonalActionService().addAccount(email, currency);
        } catch (UserNotRegisteredException | AccountAlreadyExistsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
