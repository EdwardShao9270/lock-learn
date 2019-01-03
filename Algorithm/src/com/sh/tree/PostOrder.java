package com.sh.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后序遍历 非递归&递归
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 17:23 2019/1/3
 */
public class PostOrder {
    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9,10};

        TreeNode root= TreeNode.makeBinaryTreeByArray(array,1);
        List<Integer> result =recursiveSolution(root);

        //List<Integer> result =unRecursiveSingleStack(root);

        result.forEach(System.out::println);
    }

    /**
     * 非递归实现 单栈
     * @param root
     * @return
     */
    public static List<Integer> unRecursiveSingleStack(TreeNode root){
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode preNode = null;



        while (node != null || !stack.isEmpty()){

        }
        return list;
    }

    /**
     * 递归实现
     *
     * @param root
     * @return
     */
    public static List<Integer> recursiveSolution(TreeNode root){
        List<Integer> list = new ArrayList<>();
        recursive(list,root);
        return list;
    }

    private static void recursive(List<Integer> list , TreeNode node){
        if(node != null){
            recursive(list, node.getLeft());
            recursive(list, node.getRight());
            list.add(node.getValue());

        }
    }


}
