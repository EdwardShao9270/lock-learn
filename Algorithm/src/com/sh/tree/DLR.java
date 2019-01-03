package com.sh.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历 非递归&递归
 *
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 17:16 2019/1/3
 */
public class DLR {
    public static void main(String[] args) {
        int[] array = {0,1,2,3,4,5,6,7,8,9,10};

        TreeNode root= TreeNode.makeBinaryTreeByArray(array,1);
        List<Integer> result =recursiveSolution(root);
        result.forEach(System.out::println);
    }

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
