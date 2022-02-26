package ru.itmo.entity.transactionSystem;

import ru.itmo.entity.account.AbstractAccount;

public class TopUpAccountBalance extends Transaction {
    public TopUpAccountBalance(AbstractAccount source) {
        super(source);
    }

    @Override
    public void topUp(Float sum) {
        this.getSource().getCaretaker().backup();
        this.getSource().addMoneyOnAccount(sum);
    }
}
