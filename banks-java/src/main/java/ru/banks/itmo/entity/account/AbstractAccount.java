package ru.banks.itmo.entity.account;

import ru.banks.itmo.entity.client.Client;
import ru.banks.itmo.entity.memento.Caretaker;
import ru.banks.itmo.entity.memento.ConcreteMementoImpl;
import ru.banks.itmo.entity.memento.Memento;
import ru.banks.itmo.tool.BankException;
import ru.banks.itmo.tool.UnknownMementoException;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractAccount {
    private UUID id;
    private float balance;
    private final Client accountOwner;
    private final Date creationTime;
    private BalanceInterest interestOnBalance;
    private float limit;
    private float commission;
    private Caretaker caretaker;

    protected AbstractAccount(Client accountOwner, BalanceInterest interestOnBalance, float commission, float limit) {
        this.id = UUID.randomUUID();
        this.balance = 0f;
        this.accountOwner = accountOwner;
        this.commission = commission;
        this.creationTime = new Date();
        this.interestOnBalance = interestOnBalance;
        this.limit = limit;
        this.caretaker = new Caretaker(this);
    }

    public float getBalance() {
        return balance;
    }

    public float getCommission() {
        return commission;
    }

    public float getLimit() {
        return limit;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public UUID getId() {
        return id;
    }

    public BalanceInterest getInterestOnBalance() {
        return interestOnBalance;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public Client getAccountOwner() {
        return accountOwner;
    }

    public void addMoneyOnAccount(float money) {
        balance += money;
    }

    public void getMoneyFromAccount(float money) {
        if (accountOwner.IsDoubtfulAccount() && money > limit)
            throw new BankException("You have exceeded the limit");
        balance -= money;
    }

    public Memento save() {
        return new ConcreteMementoImpl(balance);
    }

    public void restore(Memento memento) {
        if (!(memento instanceof ConcreteMementoImpl)) {
            throw new UnknownMementoException();
        }
        balance = memento.getState();
    }
}
