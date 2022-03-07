package ru.banks.itmo.entity.account;

import ru.banks.itmo.entity.client.Client;
import ru.banks.itmo.tool.BankException;

import java.util.Date;

public class DepositAccount extends AbstractAccount {
    private Float startMoney;
    private Date durationEnd;

    public DepositAccount(Client accountOwner, BalanceInterest interestOnBalance, float commission, float limit, Date durationEnd, float startMoney) {
        super(accountOwner, interestOnBalance, commission, limit);
        this.durationEnd = durationEnd;
        this.startMoney = startMoney;
    }

    @Override
    public void addMoneyOnAccount(float money) {
        if (durationEnd.getTime() <= getCreationTime().getTime()) {
            super.addMoneyOnAccount(money);
        } else {
            throw new BankException("Your deposit account duration is not end.");
        }
    }

    @Override
    public void getMoneyFromAccount(float money) {
        if (durationEnd.getTime() <= getCreationTime().getTime()) {
            super.getMoneyFromAccount(money);
        } else {
            throw new BankException("Your deposit account duration is not end.");
        }
    }
}
