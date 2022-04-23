package ru.banks.itmo.entity.bank;

import ru.banks.itmo.entity.account.BalanceInterest;

import java.util.ArrayList;

public class CentralBank {
    private List<Bank> banks;

    public CentralBank() {
        banks = new ArrayList<>();
    }

    public Bank createBank(
            String bankName,
            float bankBalanceInterest,
            float bankDepositInterest,
            float bankCommission,
            float bankLimit,
            float bankCreditLimit,
            int depositAccountDuration) {
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
