package CAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 无界队列锁
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 11:02 2018/11/22
 */
public class CLHLock implements Lock {

    private AtomicReference<QNode> tail;

    ThreadLocal<QNode> myNode;
    ThreadLocal<QNode> myPreNode;

    public CLHLock(){
        tail = new AtomicReference<>(new QNode());

        myNode = new ThreadLocal<QNode>(){
            @Override
            protected QNode initialValue(){
                return new QNode();
            }
        };

        myPreNode = new ThreadLocal<QNode>(){
            @Override
            protected QNode initialValue(){
                return null;
            }
        };
    }


    @Override
    public void lock() {
        QNode qNode = myNode.get();
        qNode.lock = true;

        QNode preNode = tail.getAndSet(qNode); //以原子方式设置为给定值，并返回以前的值。
        myPreNode.set(preNode);

        while (preNode.lock){

        }
    }

    @Override
    public void unLock() {
        QNode qNode = myNode.get();
        qNode.lock = false;
        myNode.set(myPreNode.get());
    }

    public static class QNode{
        volatile boolean lock;
    }

    @Override
    public String toString(){
        return "CLHLock";
    }
}
