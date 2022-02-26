package ru.itmo.entity.bank;

import ru.itmo.entity.account.BalanceInterest;

import java.util.ArrayList;

public class CentralBank {
    private ArrayList<Bank> banks;

    public CentralBank() {
        banks = new ArrayList<>();
    }

    public Bank createBank(
            String bankName,
            Float bankBalanceInterest,
            Float bankDepositInterest,
            Float bankCommission,
            Float bankLimit,
            Float bankCreditLimit,
            Integer depositAccountDuration) {
        var bank = new Bank(
                bankName,
                new BalanceInterest(bankBalanceInterest),
                new BalanceInterest(bankDepositInterest),
                bankCommission,
                bankLimit,
                bankCreditLimit,
                depositAccountDuration);
        banks.add(bank);
        return bank;
    }

    public Bank findBankByName(String bankName) {
        for (var bank :
                banks) {
            if (bank.getName().equals(bankName))
                return bank;
        }
        return null;
    }
}
