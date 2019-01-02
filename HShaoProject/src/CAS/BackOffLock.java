package CAS;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 回退自旋锁
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 19:48 2018/11/21
 */
public class BackOffLock implements Lock {

    private final int MAX_DELAY,MIN_DELAY;

    public  BackOffLock(int max, int min){
        this.MAX_DELAY = max;
        this.MIN_DELAY = min;
    }

    AtomicBoolean mutex = new AtomicBoolean();

    @Override
    public void lock() {

        BackOff backOff = new BackOff(MAX_DELAY,MIN_DELAY);

        while (true){
            while (mutex.get()){}
            if(!mutex.getAndSet(true)){
                return;
            }else {
                try {
                    //采用回退算法减少锁的竞争次数
                    backOff.backOff();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    @Override
    public void unLock() {
        mutex.set(false);
    }

    @Override
    public String toString(){
        return "BackOffLock";
    }
}
