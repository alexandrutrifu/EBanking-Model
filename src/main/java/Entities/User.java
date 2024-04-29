package Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import App.EBankingApp;
import App.EBankingApp.Currency;
import com.google.gson.annotations.Expose;

public class User {
    @Expose
    private String email, firstName, lastName, address;
    // TODO IMPLEMENT STATE PATTERN FOR PREMIUM ACCESS
    private UserPortfolio portfolio;
    @Expose
    private Set<User> friends;
    private File transactionHistory;

    public User() {
    }

    public User(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;

        portfolio = new UserPortfolio();

        friends = new HashSet<>();

        transactionHistory = new File("src/main/resources/transactions/USER_" + email + ".txt");
    }

    /** Adds money to the specified account.
     *
     * @param currency Currency of the account
     * @param amount Amount of money to be added
     */
    public void addMoney(Currency currency, float amount) {
        Account account = portfolio.getAccounts().get(currency);
        account.setAmount(account.getAmount() + amount);
    }

    /** Subtracts money from the specified account.
     *
     * @param currency Currency of the account
     * @param amount Amount of money to be subtracted
     */
    public void subtractMoney(Currency currency, float amount) {
        Account account = portfolio.getAccounts().get(currency);
        account.setAmount(account.getAmount() - amount);
    }

    /** Updates the transaction history of the user.
     *
     * @param transactionInfo Information about the transaction
     */
    public void updateTransactionHistory(String transactionInfo) {
        try (PrintWriter writer = new PrintWriter(transactionHistory)) {
            writer.println(transactionInfo);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    /* Getters, setters and auxiliary methods */

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public UserPortfolio getPortfolio() {
        return portfolio;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public Map<Currency, Account> getAccounts() {
        return portfolio.getAccounts();
    }
    public Map<String, Float> getStocks() {
        return portfolio.getStocks();
    }

    public String getEmail() {
        return email;
    }

    public User findFriend(User friend) {
        for (User u : friends) {
            if (u.equals(friend))
                return u;
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        for (User user : EBankingApp.getInstance().getUsers()) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
