package PersonalActions;

import App.EBankingApp;
import App.Proxy;
import Entities.User;
import App.EBankingApp.Currency;
import Exceptions.AccountAlreadyExistsException;
import Exceptions.FriendAlreadyExistsException;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotRegisteredException;

import java.util.HashSet;

/** The proxy class for PersonalAction.
 * <p>
 * It implements the same interface as the real service, but adds extra functionalities. These include: checking for corner cases, logging, etc.
 *
 */
public class PersonalActionProxy implements PersonalActionsInterface, Proxy {
    PersonalActionsInterface realService;

    public PersonalActionProxy() {
    }

    public PersonalActionProxy(PersonalActionsInterface realService) {
        this.realService = realService;
    }

    @Override
    public void createUser(User user) throws UserAlreadyExistsException {
        HashSet<User> users = EBankingApp.getInstance().getUsers();

        // Check if user is registered
        if (users.contains(user))
            throw new UserAlreadyExistsException("User with " + user.getEmail() + " already exists");

        realService.createUser(user);
        logSuccess(user.getEmail(), "CREATE USER");
    }

    @Override
    public void addFriend(String userEmail, String friendEmail) throws UserNotRegisteredException, FriendAlreadyExistsException {
        HashSet<User> users = EBankingApp.getInstance().getUsers();

        User user = User.getUserByEmail(userEmail);
        User friend = User.getUserByEmail(friendEmail);

        // Check if users are registered
        if (user == null) {
            throw new UserNotRegisteredException("User with " + userEmail + " doesn't exist");
        }
        if (friend == null) {
            throw new UserNotRegisteredException("User with " + friendEmail + " doesn't exist");
        }

        if (user.getFriends().contains(friend)) {
            throw new FriendAlreadyExistsException("User with " + friendEmail + " is already a friend");
        }

        realService.addFriend(userEmail, friendEmail);
        logSuccess(userEmail, "ADD FRIEND " + friendEmail);
    }

    @Override
    public void addAccount(String userEmail, Currency currency) throws UserNotRegisteredException, AccountAlreadyExistsException {
        User user = User.getUserByEmail(userEmail);

        // Check if user is registered
        if (user == null || !EBankingApp.getInstance().getUsers().contains(user))
            throw new UserNotRegisteredException("User with " + userEmail + " doesn't exist");

        // Check if user already has an account with the same currency
        if (user.getAccounts().containsKey(currency))
            throw new AccountAlreadyExistsException("Account in currency " + currency + " already exists for user");

        realService.addAccount(userEmail, currency);
        logSuccess(userEmail, "ADD ACCOUNT in" + " " + currency);
    }
}
