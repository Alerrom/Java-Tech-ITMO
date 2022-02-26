package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;

public class Transaction implements ITransactionChain {
    private final AbstractAccount source;
    private final AbstractAccount destination;
    private ITransactionChain transactionChain;

    public Transaction(AbstractAccount source, AbstractAccount destination) {
        this.source = source;
        this.destination = destination;
    }

    public Transaction(AbstractAccount source) {
        this.source = source;
        this.destination = null;
    }

    public AbstractAccount getSource() {
        return source;
    }

    public AbstractAccount getDestination() {
        return destination;
    }

    public ITransactionChain getTransactionChain() {
        return transactionChain;
    }

    @Override
    public ITransactionChain setNext(ITransactionChain transactionChain) {
        this.transactionChain = transactionChain;
        return transactionChain;
    }

    @Override
    public void topUp(Float sum) {
        transactionChain = new TopUpAccountBalance(source);
        transactionChain.topUp(sum);
    }

    @Override
    public void transfer(Float sum) {
        transactionChain = new TransferFromCredit(source, destination);
        transactionChain
                .setNext(new TransferFromDebit(source, destination))
                .setNext(new TransferFromDeposit(source, destination));
        transactionChain.transfer(sum);
    }

    @Override
    public void withdraw(Float sum) {
        transactionChain = new WithdrawFromCredit(source);
        transactionChain
                .setNext(new WithdrawFromDebit(source))
                .setNext(new WithdrawFromDeposit(source));
        transactionChain.withdraw(sum);
    }

    public void undo() {
        source.getCaretaker().undo();
        if (destination == null)
            return;
        destination.getCaretaker().undo();
    }
}
