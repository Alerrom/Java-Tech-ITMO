package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;
import ru.itmo.entity.account.CreditAccount;
import ru.itmo.tool.TransactionRejectedException;

public class WithdrawFromCredit extends Transaction {
    public WithdrawFromCredit(AbstractAccount source) {
        super(source);
    }

    @Override
    public void withdraw(Float sum) {
        if (this.getSource() instanceof CreditAccount creditAccount) {
            if (creditAccount.getBalance() - sum < -creditAccount.getLimit())
                throw new TransactionRejectedException("You have reached the limit");

            this.getSource().getCaretaker().backup();
            this.getSource().getMoneyFromAccount(sum);
        } else {
            this.getTransactionChain().transfer(sum);
        }
    }
}
