package CAS;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 队列锁MCSLock 试用于无cache的NUMA系统
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 12:48 2018/11/22
 */
public class MCSLock implements Lock {

    private AtomicReference<QNode> tail;

    ThreadLocal<QNode> myNode;
    public MCSLock(){
        tail = new AtomicReference<>(new QNode());
        myNode = new ThreadLocal<QNode>(){

            @Override
            protected QNode initialValue(){
                return new QNode();
            }
        };
    }

    @Override
    public void lock() {
        QNode qNode = myNode.get();
        QNode preNode = tail.getAndSet(qNode);

        if(preNode != null){
            preNode.lock = true;
            preNode.next = qNode;

            while (qNode.lock){}
        }
    }

    @Override
    public void unLock() {
        QNode qNode = myNode.get();
        if(qNode.next == null){
            if(tail.compareAndSet(qNode,null)){
                return;
            }
            //等待新的后续节点的加入，加入完毕后qNode.next不为空
            while (qNode.next == null){

            }
        }
        qNode.next.lock = false;
        qNode.next = null;

    }

    public static class QNode {
        volatile boolean lock;
        volatile QNode next;
    }

    @Override
    public String toString(){
        return "MCSLock";
    }

}
