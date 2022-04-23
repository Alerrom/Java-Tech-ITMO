package ru.banks.itmo.entity.memento;

import ru.banks.itmo.entity.account.AbstractAccount;

import java.util.ArrayList;

public class Caretaker {
    private ArrayList<Memento> mementos;
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

        Memento memento = mementos.get(mementos.size());
        mementos.remove(memento);

        account.restore(memento);
    }
}
