package ru.itmo;

import ru.itmo.entity.bank.Bank;
import ru.itmo.entity.bank.CentralBank;
import ru.itmo.entity.client.Client;
import ru.itmo.entity.client.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.tool.BankException;

public class BankTest {
    private CentralBank centralBank;
    private Bank tinkoff;
    private Bank sber;
    private Client clientOne;
    private Client clientTwo;
    private Client clientFishy;
    private Client someClient;

    @Before
    public void setup() {
        centralBank = new CentralBank();
        sber = centralBank
                .createBank("Sber",
                        0f,
                        4.0f,
                        2.5f,
                        10000f,
                        1000000f,
                        5);
        clientOne = new ClientBuilder()
                .setName("Alex")
                .setSurname("Ershov")
                .setAddress("Вязьма")
                .setPassport("123456")
                .setNotification(true)
                .getClient();
        clientTwo = new ClientBuilder()
                .setName("Alina")
                .setSurname("Pyazok")
                .setAddress("Moscow")
                .setPassport("654321")
                .setNotification(true)
                .getClient();
        clientFishy = new ClientBuilder()
                .setName("Alina")
                .setSurname("Pyazok")
                .setNotification(true)
                .getClient();
    }

    @Test
    public void createBank_CreateBank_BankCreated() {
        someClient = new ClientBuilder()
                .setName("Biba")
                .setSurname("Boba")
                .getClient();
        Assert.assertNotNull(someClient);
    }

    @Test
    public void createClient_CreateClient_ClientCreated() {
        tinkoff = centralBank
                .createBank("Tinkoff",
                        3.5f,
                        4.0f,
                        1.5f,
                        50000f,
                        5000000f,
                        15);
        Assert.assertNotNull(tinkoff);
    }

    @Test
    public void assignCreditAccountToClient_AddClientToBank_ClientAddedSuccessfullyAndCanUseHisAccount() {
        var creditAccount = sber.assignCreditAccountToClient(clientOne);
        var debitAccount = sber.assignDebitAccountToClient(clientTwo);
        sber.withdraw(creditAccount.getId(), 50f);
        Float tmp = -50f;
        Assert.assertEquals(tmp, creditAccount.getBalance());

        sber.addMoneyOnAccount(debitAccount.getId(), 5000f);
        tmp = 5000f;
        Assert.assertEquals(tmp, debitAccount.getBalance());
    }

    @Test
    public void transfer_TransferMoneyFromAccount_BalanceChangedCorrect() {
        var creditAccount = sber.assignCreditAccountToClient(clientOne);
        var debitAccount = sber.assignDebitAccountToClient(clientTwo);
        sber.addMoneyOnAccount(creditAccount.getId(), 50f);
        sber.transfer(creditAccount.getId(), debitAccount.getId(), 50f);
        Float tmp = 0f;
        Assert.assertEquals(tmp, creditAccount.getBalance());
        tmp = 50f;
        Assert.assertEquals(tmp, debitAccount.getBalance());
    }

    @Test(expected = BankException.class)
    public void isDoubtful_CreateDoubtfulAccountAndTryToAddMoney_TrowBankException() {
        var debitAccount = sber.assignDebitAccountToClient(clientFishy);
        sber.addMoneyOnAccount(debitAccount.getId(), 52000000f);
        sber.withdraw(debitAccount.getId(), 5200000000f);
    }

    @Test(expected = BankException.class)
    public void withdraw_WithdrawFromDebitAccount_TrowBankException() {
        var debitAccount = sber.assignDebitAccountToClient(clientFishy);
        sber.withdraw(debitAccount.getId(), 5200000000f);
    }
}
