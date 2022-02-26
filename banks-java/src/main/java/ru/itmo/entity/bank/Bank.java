package ru.itmo.entity.bank;

import ru.itmo.entity.account.*;
import ru.itmo.entity.client.Client;
import ru.itmo.entity.transactionSystem.Transaction;
import ru.itmo.tool.AccountDoesNotExistException;
import ru.itmo.tool.TransactionRejectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Bank {
    final private UUID id;
    private String name;
    private BalanceInterest bankBalanceInterest;
    private BalanceInterest bankDepositBalanceInterest;
    private Float bankCommission;
    private Float bankLimit;
    private Float creditLimit;
    private Integer depositAccountDuration;
    private ArrayList<Client> clients;
    private ArrayList<AbstractAccount> accounts;

    public Bank(
            String name,
            BalanceInterest bankBalanceInterest,
            BalanceInterest bankDepositBalanceInterest,
            Float bankCommission,
            Float bankLimit,
            Float creditLimit,
            Integer depositAccountDuration) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bankBalanceInterest = bankBalanceInterest;
        this.bankDepositBalanceInterest = bankDepositBalanceInterest;
        this.bankCommission = bankCommission;
        this.bankLimit = bankLimit;
        this.creditLimit = creditLimit;
        this.depositAccountDuration = depositAccountDuration;
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void addClient(Client client) {
        if (findClientById(client.getPassport()) == null)
            return;
        clients.add(client);
    }

    public DebitAccount assignDebitAccountToClient(Client client) {
        var account = new DebitAccount(client, bankBalanceInterest, bankCommission, bankLimit);
        accounts.add(account);
        return account;
    }

    public CreditAccount assignCreditAccountToClient(Client client) {
        var account = new CreditAccount(client, bankBalanceInterest, bankCommission, bankLimit);
        accounts.add(account);
        return account;
    }

    public DepositAccount assignDepositAccountToClient(Client client, Float startMoney) {
        var account = new DepositAccount(client, bankBalanceInterest, bankCommission, bankLimit, new Date(), startMoney);
        accounts.add(account);
        return account;
    }

    public void sendNotificationsForClients(String message) {
        var tmp = new ArrayList<Client>();
        for (var client :
                clients) {
            if (client.getNotify())
                tmp.add(client);
        }

        for (var client :
                tmp) {
            client.getNotificationHistory().add(message);
        }
    }

    public void addAccountInterests() {
        for (var account
                : accounts) {
            account.getInterestOnBalance().setInterest((account.getBalance() * account.getInterestOnBalance().getInterest()));
        }
    }

    public void addInterestToAccountBalance() {
        for (var account :
                accounts) {
            account.addMoneyOnAccount(account.getInterestOnBalance().getInterest());
            account.getInterestOnBalance().setInterest(0f);
        }
    }

    public void transferMoney(UUID sourceId, UUID destinationId, Float value) {
        var source = findAccountById(sourceId);
        var destination = findAccountById(destinationId);
        if (source == null || destination == null)
            return;
        source.getMoneyFromAccount(value);
        destination.addMoneyOnAccount(value);
    }

    public void setBalanceInterest(Float value) {
        bankBalanceInterest.setInterest(value);
        sendNotificationsForClients("Balance interest is " + value.toString() + "now");
    }

    public void setDepositBalanceInterest(Float value) {
        bankDepositBalanceInterest.setInterest(value);
        sendNotificationsForClients("Deposit balance interest is " + value.toString() + "now");
    }

    public void setBankCreditCommission(Float value) {
        bankCommission = value;
        sendNotificationsForClients("Bank credit commission is " + value.toString() + "now");
    }

    public void setBankCreditLimit(Float value) {
        bankLimit = value;
        sendNotificationsForClients("Bank credit limit is " + value.toString() + "now");
    }

    public void setDepositAccountDuration(Integer value) {
        depositAccountDuration = value;
        sendNotificationsForClients("Bank deposit accounts duration is " + value.toString() + "now");
    }


    public void addMoneyOnAccount(UUID id, Float sum) {
        var account = findAccountById(id);
        if (account == null)
            return;
        new Transaction(account).topUp(sum);
    }

    public void withdraw(UUID id, Float sum) {
        AbstractAccount account = findAccountById(id);
        if (account == null)
            throw new AccountDoesNotExistException();

        if (account.getAccountOwner().IsDoubtfulAccount() && bankLimit < sum)
            throw new TransactionRejectedException("You have exceeded the limit");

        new Transaction(account).withdraw(sum);
    }

    public void transfer(UUID sourceId, UUID destinationId, Float sum) {
        AbstractAccount source = findAccountById(sourceId);
        if (source == null)
            throw new AccountDoesNotExistException();

        AbstractAccount destination = findAccountById(destinationId);
        if (destination == null)
            throw new AccountDoesNotExistException();

        if (source.getAccountOwner().IsDoubtfulAccount() && bankLimit < sum)
            throw new TransactionRejectedException("You have exceeded the limit");

        new Transaction(source, destination).transfer(sum);
    }

    public void rejectTransaction(UUID sourceId, UUID destinationId) {
        AbstractAccount source = findAccountById(sourceId);
        if (source == null)
            throw new AccountDoesNotExistException();

        AbstractAccount destination = findAccountById(destinationId);
        if (destination == null)
            throw new AccountDoesNotExistException();

        new Transaction(source, destination).undo();
    }

    private AbstractAccount findAccountById(UUID id) {
        for (var account :
                accounts) {
            if (account.getId() == id)
                return account;
        }
        return null;
    }

    private Client findClientById(String passport) {
        for (var client :
                clients) {
            if (client.getPassport().equals(passport))
                return client;
        }
        return null;
    }
}