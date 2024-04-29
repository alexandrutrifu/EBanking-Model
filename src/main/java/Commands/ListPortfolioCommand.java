package Commands;

import Exceptions.UserNotRegisteredException;

public class ListPortfolioCommand extends Command {
    public ListPortfolioCommand() {
        super();
    }

    public ListPortfolioCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        try {
            appManager.getEntityLister().listPortfolio(tokens[2]);
        } catch (UserNotRegisteredException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
