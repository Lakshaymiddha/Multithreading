package com.examples.locks;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BankAccount {
    Logger logger = Logger.getLogger(this.getClass().getName());
    protected int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public abstract void withdraw(int amount);
}
