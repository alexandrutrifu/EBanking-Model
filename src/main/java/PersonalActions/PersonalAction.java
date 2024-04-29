package PersonalActions;

import App.EBankingApp;
import Entities.Account;
import Entities.User;
import App.EBankingApp.Currency;

public class PersonalAction implements PersonalActionsInterface {
    @Override
    public void createUser(User user) {
        EBankingApp.getInstance().getUsers().add(user);
    }

    @Override
    public void addFriend(String userEmail, String friendEmail) {
        User user = User.getUserByEmail(userEmail);
        User friend = User.getUserByEmail(friendEmail);

        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }

    @Override
    public void addAccount(String userEmail, Currency currency) {
        User user = User.getUserByEmail(userEmail);

        user.getAccounts().put(currency, new Account(currency, userEmail));
    }
}
