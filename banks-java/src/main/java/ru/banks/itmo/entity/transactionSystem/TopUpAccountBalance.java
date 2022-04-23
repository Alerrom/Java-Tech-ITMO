package ru.banks.itmo.entity.transactionSystem;

import ru.banks.itmo.entity.account.AbstractAccount;

public class TopUpAccountBalance extends TransactionImpl {
    public TopUpAccountBalance(AbstractAccount source) {
        super(source);
    }

    @Override
    public void topUp(float sum) {
        this.getSource().getCaretaker().backup();
        this.getSource().addMoneyOnAccount(sum);
    }
}
