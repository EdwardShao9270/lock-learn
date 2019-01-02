package CAS;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 测试——设置自旋锁
 *
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 19:18 2018/11/21
 */
public class TestAndSetLock implements Lock{

    private AtomicBoolean mutex =  new AtomicBoolean();

    @Override
    public void lock() {

        /*
        * getAndSet方法会设置mutex的值为true，并返回变化前的值
        * 当mutex为false时才返回，表示获取锁
        * getAndSet是原子操作，它的改动对所有变量均可见
        *
        * */

        while (mutex.getAndSet(true)){

        }

    }

    @Override
    public void unLock() {
        mutex.set(false);
    }

    @Override
    public String toString(){
        return "TestAndSetLock";
    }
}
