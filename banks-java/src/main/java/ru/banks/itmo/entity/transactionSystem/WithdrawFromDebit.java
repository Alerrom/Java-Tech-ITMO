package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;
import ru.banks.itmo.entity.account.DebitAccount;
import ru.banks.itmo.tool.TransactionRejectedException;

public class WithdrawFromDebit extends TransactionImpl {
    public WithdrawFromDebit(AbstractAccount source) {
        super(source);
    }

    @Override
    public void withdraw(float sum) {
        if (this.getSource() instanceof DebitAccount debitAccount) {
            if (sum > debitAccount.getBalance())
                throw new TransactionRejectedException("You have reached the limit");

            this.getSource().getCaretaker().backup();
            this.getSource().getMoneyFromAccount(sum);
        } else {
            this.getTransactionChain().transfer(sum);
        }
    }
}
