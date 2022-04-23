package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;
import ru.banks.itmo.entity.account.CreditAccount;
import ru.banks.itmo.tool.TransactionRejectedException;

public class TransferFromCredit extends TransactionImpl {
    public TransferFromCredit(AbstractAccount source, AbstractAccount destination) {
        super(source, destination);
    }

    @Override
    public void transfer(float sum) {
        if (this.getSource() instanceof CreditAccount creditAccount) {
            if (creditAccount.getBalance() - sum < -creditAccount.getLimit()) {
                throw new TransactionRejectedException("You have reached the limit");
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
