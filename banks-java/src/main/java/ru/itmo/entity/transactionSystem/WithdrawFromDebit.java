package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;
import ru.itmo.entity.account.DebitAccount;
import ru.itmo.tool.TransactionRejectedException;

public class WithdrawFromDebit extends Transaction {
    public WithdrawFromDebit(AbstractAccount source) {
        super(source);
    }

    @Override
    public void withdraw(Float sum) {
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
