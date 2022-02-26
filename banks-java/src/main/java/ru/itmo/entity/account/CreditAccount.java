package ru.itmo.entity.account;

import ru.itmo.entity.client.Client;
import ru.itmo.tool.BankException;

public class CreditAccount extends AbstractAccount {
    public CreditAccount(Client accountOwner, BalanceInterest interestOnBalance, Float commission, float limit) {
        super(accountOwner, interestOnBalance, commission, limit);
    }

    @Override
    public void addMoneyOnAccount(Float money) {
        if (this.getBalance() < 0) {
            this.addMoneyOnAccount(money - (this.getCommission() * money));
        } else {
            super.addMoneyOnAccount(money);
        }
    }

    @Override
    public void getMoneyFromAccount(Float money) {
        if (getBalance() < 0 && getBalance() - money > -getLimit()) {
            super.addMoneyOnAccount(money - (getCommission() * money));
        } else if (getBalance() - money <= -getLimit()) {
            throw new BankException("You have reached your credit limit");
        } else {
            super.getMoneyFromAccount(money);
        }
    }
}
