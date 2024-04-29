package PersonalActions;

import App.EBankingApp;
import Entities.User;
import App.EBankingApp.Currency;
import Exceptions.AccountAlreadyExistsException;
import Exceptions.FriendAlreadyExistsException;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UserNotRegisteredException;

public interface PersonalActionsInterface {
    void createUser(User user) throws UserAlreadyExistsException;
    void addFriend(String userEmail, String friendEmail) throws UserNotRegisteredException, FriendAlreadyExistsException;
    void addAccount(String userEmail, Currency currency) throws UserNotRegisteredException, AccountAlreadyExistsException;
}
