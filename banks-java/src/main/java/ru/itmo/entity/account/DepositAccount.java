package ru.itmo.entity.account;

import ru.itmo.entity.client.Client;
import ru.itmo.tool.BankException;

import java.util.Date;

public class DepositAccount extends AbstractAccount {
    private Float startMoney;
    private Date durationEnd;

    public DepositAccount(Client accountOwner, BalanceInterest interestOnBalance, Float commission, Float limit, Date durationEnd, Float startMoney) {
        super(accountOwner, interestOnBalance, commission, limit);
        this.durationEnd = durationEnd;
        this.startMoney = startMoney;
    }

    @Override
    public void addMoneyOnAccount(Float money) {
        if (durationEnd.getTime() <= getCreationTime().getTime()) {
            super.addMoneyOnAccount(money);
        } else {
            throw new BankException("Your deposit account duration is not end.");
        }
    }

    @Override
    public void getMoneyFromAccount(Float money) {
        if (durationEnd.getTime() <= getCreationTime().getTime()) {
            super.getMoneyFromAccount(money);
        } else {
            throw new BankException("Your deposit account duration is not end.");
        }
    }
}
