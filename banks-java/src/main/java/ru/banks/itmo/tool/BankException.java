package ru.banks.itmo.tool;

public class BankException extends RuntimeException {
    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Exception innerException) {
        super(message, innerException);
    }
}
