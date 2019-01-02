package CAS;

import java.util.Random;

/**
 * 回退算法 降低锁竞争的几率
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 19:50 2018/11/21
 */
public class BackOff {

    private final int MAX_DELAY,MIN_DELAY;

    private int limit;

    private final Random random;


    public BackOff(int max, int min) {
        MAX_DELAY = max;
        MIN_DELAY = min;
        limit = MIN_DELAY;
        random = new Random();
    }

    public void backOff() throws InterruptedException {
        int delay = random.nextInt(limit);
        limit = Math.min(MAX_DELAY, 2*limit);
        Thread.sleep(delay);
    }
}
