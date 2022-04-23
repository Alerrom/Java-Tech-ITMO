package ru.banks.itmo.entity.account;

public class BalanceInterest {
    private float interest;

    public BalanceInterest(float value) {
        this.interest = value;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }
}
