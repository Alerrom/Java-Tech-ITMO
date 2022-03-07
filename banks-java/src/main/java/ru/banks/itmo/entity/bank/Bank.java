package ru.banks.itmo.entity.bank;

import ru.banks.itmo.entity.account.*;
import ru.banks.itmo.entity.client.Client;
import ru.banks.itmo.entity.transactionSystem.TransactionImpl;
import ru.banks.itmo.tool.AccountDoesNotExistException;
import ru.banks.itmo.tool.TransactionRejectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Bank {
    final private UUID id;
    private String name;
    private BalanceInterest bankBalanceInterest;
    private BalanceInterest bankDepositBalanceInterest;
    private float bankCommission;
    private Float bankLimit;
    private float creditLimit;
    private int depositAccountDuration;
    private List<Client> clients;
    private List<AbstractAccount> accounts;

    public Bank(
            String name,
            BalanceInterest bankBalanceInterest,
            BalanceInterest bankDepositBalanceInterest,
            float bankCommission,
            float bankLimit,
            float creditLimit,
            int depositAccountDuration) {
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

    public DepositAccount assignDepositAccountToClient(Client client, float startMoney) {
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

    public void transferMoney(UUID sourceId, UUID destinationId, float value) {
        var source = findAccountById(sourceId);
        var destination = findAccountById(destinationId);
        if (source == null || destination == null)
            return;
        source.getMoneyFromAccount(value);
        destination.addMoneyOnAccount(value);
    }

    public void setBalanceInterest(float value) {
        bankBalanceInterest.setInterest(value);
        sendNotificationsForClients(String.format("Balance interest is %f now", value));
    }

    public void setDepositBalanceInterest(float value) {
        bankDepositBalanceInterest.setInterest(value);
        sendNotificationsForClients(String.format("Deposit balance interest is %f now", value));
    }

    public void setBankCreditCommission(float value) {
        bankCommission = value;
        sendNotificationsForClients(String.format("Bank credit commission is %f now", value));
    }

    public void setBankCreditLimit(float value) {
        bankLimit = value;
        sendNotificationsForClients(String.format("Bank credit limit is %f now", value));
    }

    public void setDepositAccountDuration(int value) {
        depositAccountDuration = value;
        sendNotificationsForClients(String.format("Bank deposit accounts duration is %d now", value));
    }


    public void addMoneyOnAccount(UUID id, float sum) {
        var account = findAccountById(id);
        if (account == null)
            return;
        new TransactionImpl(account).topUp(sum);
    }

    public void withdraw(UUID id, float sum) {
        AbstractAccount account = findAccountById(id);
        if (account == null)
            throw new AccountDoesNotExistException();

        if (account.getAccountOwner().IsDoubtfulAccount() && bankLimit < sum)
            throw new TransactionRejectedException("You have exceeded the limit");

        new TransactionImpl(account).withdraw(sum);
    }

    public void transfer(UUID sourceId, UUID destinationId, float sum) {
        AbstractAccount source = findAccountById(sourceId);
        if (source == null)
            throw new AccountDoesNotExistException();

        AbstractAccount destination = findAccountById(destinationId);
        if (destination == null)
            throw new AccountDoesNotExistException();

        if (source.getAccountOwner().IsDoubtfulAccount() && bankLimit < sum)
            throw new TransactionRejectedException("You have exceeded the limit");

        new TransactionImpl(source, destination).transfer(sum);
    }

    public void rejectTransaction(UUID sourceId, UUID destinationId) {
        AbstractAccount source = findAccountById(sourceId);
        if (source == null)
            throw new AccountDoesNotExistException();

        AbstractAccount destination = findAccountById(destinationId);
        if (destination == null)
            throw new AccountDoesNotExistException();

        new TransactionImpl(source, destination).undo();
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