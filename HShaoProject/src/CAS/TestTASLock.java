package CAS;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 测试———测试——自旋锁
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 19:35 2018/11/21
 */
public class TestTASLock implements Lock {

    AtomicBoolean mutex = new AtomicBoolean();

    @Override
    public void lock() {
        while (true){
            while (mutex.get()){}

            if (!mutex.getAndSet(true)){
                return;
            }
        }
    }

    @Override
    public void unLock() {
        mutex.set(false);
    }

    @Override
    public String toString() {
        return "TestTASLock";
    }
}
