package ru.banks.itmo.entity.memento;

public class ConcreteMementoImpl implements Memento {
    private float balanceState;

    public ConcreteMementoImpl(float balanceState) {
        this.balanceState = balanceState;
    }

    @Override
    public float getState() {
        return balanceState;
    }
}
