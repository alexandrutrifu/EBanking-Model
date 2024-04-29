package Commands;

import Exceptions.FriendAlreadyExistsException;
import Exceptions.UserNotRegisteredException;

public class AddFriendCommand extends Command {
    public AddFriendCommand() {
        super();
    }

    public AddFriendCommand(String[] tokens) {
        super(tokens);
    }

    @Override
    public void execute(String[] tokens) {
        String userEmail = tokens[2];
        String friendEmail = tokens[3];

        try {
            appManager.getPersonalActionService().addFriend(userEmail, friendEmail);
        } catch (UserNotRegisteredException | FriendAlreadyExistsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
