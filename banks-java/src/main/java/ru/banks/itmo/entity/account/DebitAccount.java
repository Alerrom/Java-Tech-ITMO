package ru.banks.itmo.entity.account;

import ru.banks.itmo.entity.client.Client;

public class DebitAccount extends AbstractAccount {
    public DebitAccount(Client accountOwner, BalanceInterest interestOnBalance, float commission, float limit) {
        super(accountOwner, interestOnBalance, commission, limit);
    }
}
