package CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有界队列锁
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 10:06 2018/11/22
 */
public class BoundedQueueLock  implements Lock{

    private volatile Boolean flags[];

    private AtomicInteger tail;

    private final int capacity;

    public BoundedQueueLock(int capacity){
        this.capacity = capacity;
        flags = new Boolean[this.capacity];
        tail = new AtomicInteger(0);
        flags[0] = true;
    }


    public ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };

    @Override
    public void lock() {
        int slot = tail.getAndIncrement() % capacity;
        mySlotIndex.set(slot);

        while (!flags[slot]) {}

    }

    @Override
    public void unLock() {
        int slot = mySlotIndex.get();
        flags[slot] = false;
        flags[(slot+1)% capacity] = true;
    }

    @Override
    public String toString(){
        return "BoundedQueueLock";
    }
}
