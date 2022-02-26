package ru.itmo.entity.memento;

public class ConcreteMemento implements IMemento {
    private Float balanceState;

    public ConcreteMemento(Float balanceState) {
        this.balanceState = balanceState;
    }

    @Override
    public Float getState() {
        return balanceState;
    }
}
