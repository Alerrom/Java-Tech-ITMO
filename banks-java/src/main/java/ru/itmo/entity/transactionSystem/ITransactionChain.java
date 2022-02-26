package ru.itmo.entity.transactionSystem;

public interface ITransactionChain {
    ITransactionChain setNext(ITransactionChain transactionChain);

    void topUp(Float sum);

    void transfer(Float sum);

    void withdraw(Float sum);
}
