package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;
import ru.banks.itmo.entity.account.CreditAccount;
import ru.banks.itmo.tool.TransactionRejectedException;

public class WithdrawFromCredit extends TransactionImpl {
    public WithdrawFromCredit(AbstractAccount source) {
        super(source);
    }

    @Override
    public void withdraw(float sum) {
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
