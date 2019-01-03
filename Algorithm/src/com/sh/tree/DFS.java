package com.sh.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 16:41 2019/1/2
 */
public class DFS {

    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9,10};

        TreeNode root= TreeNode.makeBinaryTreeByArray(array,1);
        //List<Integer> result = stackMethod(root);
        List<Integer> result = recursiveSolution(root);
        result.forEach(System.out::println);
    }

    /**
     * 非递归遍历
     * @param root
     * @return
     */
    public static List<Integer> stackMethod(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        stack.push(root);
        while (!stack.empty()){
            TreeNode node = stack.pop();

            if(node.getRight() != null){
                stack.push(node.getRight());
            }

            if(node.getLeft() != null){
                stack.push(node.getLeft());
            }
            result.add(node.getValue());
        }
        return result;
    }

    /**
     * 递归遍历
     * @param node
     * @return
     */

    public static List<Integer> recursiveSolution(TreeNode node){
        List<Integer> list = new ArrayList<>();

        recursive(list,node);
        return list;
    }

    private static void recursive(List<Integer> list,TreeNode node){
        if (node!=null) {
            list.add(node.getValue());
            recursive(list,node.getLeft());
            recursive(list,node.getRight());
        }
    }
}
