package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;
import ru.itmo.entity.account.DepositAccount;
import ru.itmo.tool.TransactionRejectedException;

public class TransferFromDeposit extends Transaction {
    public TransferFromDeposit(AbstractAccount source, AbstractAccount destination) {
        super(source, destination);
    }

    @Override
    public void transfer(Float sum) {
        if (this.getSource() instanceof DepositAccount depositAccount) {
            if (sum > depositAccount.getBalance()) {
                throw new TransactionRejectedException("Insufficient funds on the account");
            }
            this.getSource().getCaretaker().backup();
            this.getDestination().getCaretaker().backup();

            this.getSource().getMoneyFromAccount(sum);
            this.getDestination().addMoneyOnAccount(sum);
        } else {
            this.getTransactionChain().transfer(sum);
        }
    }
}
