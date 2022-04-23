package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;
import ru.banks.itmo.entity.account.DepositAccount;
import ru.banks.itmo.tool.TransactionRejectedException;

public class TransferFromDeposit extends TransactionImpl {
    public TransferFromDeposit(AbstractAccount source, AbstractAccount destination) {
        super(source, destination);
    }

    @Override
    public void transfer(float sum) {
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
