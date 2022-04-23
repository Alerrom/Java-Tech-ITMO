package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;

public class TransactionImpl implements TransactionChain {
    private final AbstractAccount source;
    private final AbstractAccount destination;
    private TransactionChain transactionChain;

    public TransactionImpl(AbstractAccount source, AbstractAccount destination) {
        this.source = source;
        this.destination = destination;
    }

    public TransactionImpl(AbstractAccount source) {
        this.source = source;
        this.destination = null;
    }

    public AbstractAccount getSource() {
        return source;
    }

    public AbstractAccount getDestination() {
        return destination;
    }

    public TransactionChain getTransactionChain() {
        return transactionChain;
    }

    @Override
    public TransactionChain setNext(TransactionChain transactionChain) {
        this.transactionChain = transactionChain;
        return transactionChain;
    }

    @Override
    public void topUp(float sum) {
        transactionChain = new TopUpAccountBalance(source);
        transactionChain.topUp(sum);
    }

    @Override
    public void transfer(float sum) {
        transactionChain = new TransferFromCredit(source, destination);
        transactionChain
                .setNext(new TransferFromDebit(source, destination))
                .setNext(new TransferFromDeposit(source, destination));
        transactionChain.transfer(sum);
    }

    @Override
    public void withdraw(float sum) {
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
