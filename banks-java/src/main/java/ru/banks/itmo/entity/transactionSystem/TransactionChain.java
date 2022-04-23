package ru.banks.itmo.entity.transactionSystem;

public interface TransactionChain {
    TransactionChain setNext(TransactionChain transactionChain);

    void topUp(float sum);

    void transfer(float sum);

    void withdraw(float sum);
}
