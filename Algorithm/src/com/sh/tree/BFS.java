package com.sh.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 广度优先遍历&层序遍历 非递归&递归
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 16:21 2019/1/3
 */
public class BFS {

    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9,10};
        TreeNode root = TreeNode.makeBinaryTreeByArray(array,1);
        List<Integer> result = queueMethod(root);
        result.forEach(System.out::println);
    }

    /**
     * 非递归方式
     * @param root
     * @return
     */
    public static List<Integer> queueMethod(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        if(root == null){
            return list;
        }

        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.remove();

            if(node.getLeft() != null){
                queue.add(node.getLeft());
            }
            if(node.getRight() != null){
                queue.add(node.getRight());
            }

            list.add(node.getValue());
        }
        return list;
    }



}
