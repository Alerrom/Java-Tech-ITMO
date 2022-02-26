package ru.itmo.entity.account;

import ru.itmo.entity.client.Client;
import ru.itmo.entity.memento.Caretaker;
import ru.itmo.entity.memento.ConcreteMemento;
import ru.itmo.entity.memento.IMemento;
import ru.itmo.tool.BankException;
import ru.itmo.tool.UnknownMementoException;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractAccount {
    private UUID id;
    private Float balance;
    private final Client accountOwner;
    private final Date creationTime;
    private BalanceInterest interestOnBalance;
    private Float limit;
    private Float commission;
    private Caretaker caretaker;

    protected AbstractAccount(Client accountOwner, BalanceInterest interestOnBalance, Float commission, Float limit) {
        this.id = UUID.randomUUID();
        this.balance = 0f;
        this.accountOwner = accountOwner;
        this.commission = commission;
        this.creationTime = new Date();
        this.interestOnBalance = interestOnBalance;
        this.limit = limit;
        this.caretaker = new Caretaker(this);
    }

    public Float getBalance() {
        return balance;
    }

    public Float getCommission() {
        return commission;
    }

    public Float getLimit() {
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

    public void addMoneyOnAccount(Float money) {
        balance += money;
    }

    public void getMoneyFromAccount(Float money) {
        if (accountOwner.IsDoubtfulAccount() && money > limit)
            throw new BankException("You have exceeded the limit");
        balance -= money;
    }

    public IMemento save() {
        return new ConcreteMemento(balance);
    }

    public void restore(IMemento memento) {
        if (!(memento instanceof ConcreteMemento)) {
            throw new UnknownMementoException();
        }
        balance = memento.getState();
    }
}
