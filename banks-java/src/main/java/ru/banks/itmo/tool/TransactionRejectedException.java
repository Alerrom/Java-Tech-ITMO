package ru.banks.itmo.tool;

public class TransactionRejectedException extends BankException {
    public TransactionRejectedException(String message) {
        super(message);
    }
}