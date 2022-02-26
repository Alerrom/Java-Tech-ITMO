package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;
import ru.itmo.entity.account.DebitAccount;
import ru.itmo.tool.TransactionRejectedException;

public class TransferFromDebit extends Transaction {
    public TransferFromDebit(AbstractAccount source, AbstractAccount destination) {
        super(source, destination);
    }

    @Override
    public void transfer(Float sum) {
        if (this.getSource() instanceof DebitAccount debitAccount) {
            if (sum > debitAccount.getBalance())
                throw new TransactionRejectedException("Insufficient funds on the account");

            this.getSource().getCaretaker().backup();
            this.getDestination().getCaretaker().backup();

            this.getSource().getMoneyFromAccount(sum);
            this.getDestination().addMoneyOnAccount(sum);
        } else {
            this.getTransactionChain().transfer(sum);
        }
    }
}
