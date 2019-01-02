package lock;

import CAS.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 简单的可重入锁实现，使用一个计数器记录当前线程重入锁的次数，获得锁时计数器加1，释放锁时计数器减1，当计数器等于0时表示释放了锁
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 14:17 2018/11/23
 */
public class SimpleReentrantLock implements Lock {

    private volatile Thread exclusiveOwnerThread;

    private volatile int holdCount;

    private final java.util.concurrent.locks.Lock lock;

    private final Condition isCountZero;

    public SimpleReentrantLock(){
        lock = new ReentrantLock();
        isCountZero = lock.newCondition();
        holdCount = 0;
    }


    @Override
    public void lock() {
        Thread currentThread = Thread.currentThread();
        if(exclusiveOwnerThread == currentThread){
            holdCount ++;
            return;
        }
        while (holdCount != 0){
            try {
                isCountZero.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exclusiveOwnerThread = currentThread;
        holdCount++;

    }

    @Override
    public void unLock() {
        lock.lock();
        try {
            holdCount --;
            if(holdCount == 0){
                isCountZero.signalAll();
            }

        }finally {
            lock.unlock();
        }

    }
}
