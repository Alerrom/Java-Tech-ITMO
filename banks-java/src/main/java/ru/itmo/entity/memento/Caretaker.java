package ru.itmo.entity.memento;

import ru.itmo.entity.account.AbstractAccount;

import java.util.ArrayList;

public class Caretaker {
    private ArrayList<IMemento> mementos;
    private final AbstractAccount account;

    public Caretaker(AbstractAccount account) {
        this.account = account;
        this.mementos = new ArrayList<>();
    }

    public void backup() {
        mementos.add(account.save());
    }

    public void undo() {
        if (mementos.isEmpty())
            return;

        IMemento memento = mementos.get(mementos.size());
        mementos.remove(memento);

        account.restore(memento);
    }
}
