package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;
import ru.banks.itmo.entity.account.DepositAccount;
import ru.banks.itmo.tool.TransactionRejectedException;

public class WithdrawFromDeposit extends TransactionImpl {
    public WithdrawFromDeposit(AbstractAccount source) {
        super(source);
    }

    @Override
    public void withdraw(float sum) {
        if (this.getSource() instanceof DepositAccount depositAccount) {
            if (sum > depositAccount.getBalance())
                throw new TransactionRejectedException("Insufficient funds on the account");

            this.getSource().getCaretaker().backup();
            this.getSource().getMoneyFromAccount(sum);
        } else {
            this.getTransactionChain().transfer(sum);
        }
    }
}
