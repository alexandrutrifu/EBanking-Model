package Commands;

import Exceptions.UserNotRegisteredException;

public class ListUserCommand extends Command {
    public ListUserCommand() {
        super();
    }

    public ListUserCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        try {
            appManager.getEntityLister().listUser(tokens[2]);
        } catch (UserNotRegisteredException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
