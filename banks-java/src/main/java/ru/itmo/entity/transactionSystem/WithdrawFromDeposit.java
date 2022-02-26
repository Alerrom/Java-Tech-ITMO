package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;
import ru.itmo.entity.account.DepositAccount;
import ru.itmo.tool.TransactionRejectedException;

public class WithdrawFromDeposit extends Transaction {
    public WithdrawFromDeposit(AbstractAccount source) {
        super(source);
    }

    @Override
    public void withdraw(Float sum) {
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
