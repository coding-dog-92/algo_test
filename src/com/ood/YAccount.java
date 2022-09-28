package com.ood;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class YAccount {
    // 分
    private int amount;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public YAccount() {
        this.amount = 0;
    }

    public void deposit(int money) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" ready to deposit: "+money);
            this.amount += money;
            System.out.println(Thread.currentThread().getName()+" deposit, current amount: "+amount);
        } catch (Exception e) {

        } finally {
            lock.writeLock().unlock();
        }
    }

    public void withdraw (int money) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" ready to withdraw: "+money);;
            // check
            if(money>amount) {
                System.out.println(Thread.currentThread().getName()+" withdraw failed, current money: "+amount);
            } else {
                this.amount -= money;
                System.out.println(Thread.currentThread().getName()+" withdraw, current money: "+amount);
            }
        } catch (Exception e) {

        }finally {
            lock.writeLock().unlock();
        }
    }

    public int amount () {
        lock.readLock().lock();
        int num = 0;
        System.out.println(Thread.currentThread().getName()+" ready to get current money");
        try {
            num = this.amount;
        } catch (Exception e) {

        } finally {
            lock.readLock().unlock();
        }
        System.out.println(Thread.currentThread().getName()+" et current money: "+ num);
        return num;
    }

    public static void main(String[] args) {
        final YAccount account = new YAccount();
        new Thread(() -> {
            account.amount();
            account.deposit(1000);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdraw(1000);
//            int i=0;
//            while (i++<100) {
//                account.deposit(new Random().nextInt(20)*10);
//            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.amount();
            account.withdraw(1000);
//            int i=0;
//            while (i++<100) {
//                account.amount();
//            }
        }).start();

        new Thread(() -> {
            account.amount();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdraw(1000);
//            int i=0;
//            while (i++<100) {
//                account.withdraw(new Random().nextInt(30)*10);
//            }
        }).start();
    }
}
