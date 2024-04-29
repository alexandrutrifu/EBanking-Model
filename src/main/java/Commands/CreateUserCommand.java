package Commands;

import Entities.User;
import Exceptions.UserAlreadyExistsException;

public class CreateUserCommand extends Command {
    public CreateUserCommand() {
        super();
    }

    public CreateUserCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String email = tokens[2];
        String firstName = tokens[3];
        String lastName = tokens[4];
        StringBuilder address = new StringBuilder();

        for (int i = 5; i < tokens.length; i++) {
            address.append(tokens[i]).append(" ");
        }

        User user = new User(tokens[2], tokens[3], tokens[4], new String(address));

        try {
            appManager.getPersonalActionService().createUser(user);
        } catch (UserAlreadyExistsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
