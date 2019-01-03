package com.sh.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历 非递归&递归
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 17:16 2019/1/3
 */
public class PreOrder {
    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9,10};

        TreeNode root= TreeNode.makeBinaryTreeByArray(array,1);
        //List<Integer> result =recursiveSolution(root);

        List<Integer> result =unRecursive(root);

        result.forEach(System.out::println);
    }


    /**
     * 非递归实现
     * @param root
     * @return
     */
    public static List<Integer> unRecursive(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        if(root == null){
            return list;
        }
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.getValue());

            if(node.getRight() != null){
                stack.push(node.getRight());
            }

            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }
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

        if (node != null){
            list.add(node.getValue());
            recursive(list,node.getLeft());
            recursive(list,node.getRight());
        }

    }
}
