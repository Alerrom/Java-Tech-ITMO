package ru.itmo.entity.account;

import ru.itmo.entity.client.Client;

public class DebitAccount extends AbstractAccount {
    public DebitAccount(Client accountOwner, BalanceInterest interestOnBalance, Float commission, Float limit) {
        super(accountOwner, interestOnBalance, commission, limit);
    }
}
