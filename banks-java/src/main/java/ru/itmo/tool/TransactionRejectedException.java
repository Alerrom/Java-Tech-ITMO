package ru.itmo.tool;

public class TransactionRejectedException extends BankException {
    public TransactionRejectedException(String message) {
        super(message);
    }
}