package Entities;

import App.EBankingApp;
import App.EBankingApp.Currency;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    @Expose
    Currency currency;
    @Expose
    float amount;
    String ownerEmail;

    public Account() {
    }

    public Account(Currency currency, String email) {
        this.currency = currency;
        this.ownerEmail = email;
        this.amount = 0;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return currency == account.currency && Objects.equals(ownerEmail, account.ownerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, ownerEmail);
    }
}
