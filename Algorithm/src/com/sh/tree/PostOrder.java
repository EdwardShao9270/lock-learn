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
        //List<Integer> result =recursiveSolution(root);

        //List<Integer> result =unRecursiveSingleStack(root);
        List<Integer> result =unRecursiveDoubleStack(root);

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
        //增加被访问过的右节点记录
        TreeNode preNode = null;

        while (node != null || !stack.isEmpty()){
            while (node !=null){
                stack.push(node);
                node = node.getLeft();
            }

            if(!stack.isEmpty()){
                TreeNode tmpNode = stack.peek().getRight();
                //一个根节点被访问的情况，是节点的右节点为空或者右节点被访问过
                if(tmpNode == null || preNode == tmpNode){
                    node = stack.pop();
                    list.add(node.getValue());
                    preNode = node;
                    node = null;
                }else{
                    //如果存在右子节点，则处理
                    node = tmpNode;
                }
            }
        }
        return list;
    }

    /**
     * 非递归实现 双栈
     * @param root
     * @return
     */
    public static List<Integer> unRecursiveDoubleStack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> tmpStack = new Stack<>();

        TreeNode node = root;

        while(node != null || !stack.isEmpty()){

            //将根节点及其右子节点压入栈
            while (node != null) {
                stack.push(node);
                tmpStack.push(node);
                node = node.getRight();
            }

            //处理栈顶节点的左子树
            if(!stack.isEmpty()){
                node = stack.pop();
                list.add(node.getValue());
                node = node.getLeft();
            }
        }

        while(!tmpStack.isEmpty()){
            node = tmpStack.pop();
            list.add(node.getValue());
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
