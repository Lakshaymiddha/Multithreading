package com.examples.locks;

import java.util.logging.Level;

public class BankAccountSynchronized extends BankAccount {
    public BankAccountSynchronized(int balance) {
        super(balance);
    }

    @Override
    public synchronized void withdraw(int amount) {
        if (amount <= balance) {
            logger.log(Level.INFO, "Processing withdraw of amount: {0}", amount);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "Withdraw process interrupted during withdraw of amount: {0}", amount);
            }
            balance -= amount;
            logger.log(Level.INFO, "Remaining balance: {0}", balance);
        } else {
            logger.log(Level.INFO, "Insufficient balance: {0}", balance);
        }
    }
}
