package ru.itmo.entity.account;

public class BalanceInterest {
    private Float interest;

    public BalanceInterest(Float value) {
        this.interest = value;
    }

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        this.interest = interest;
    }
}
