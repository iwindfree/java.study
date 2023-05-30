package io.windfree.algorithm.tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
input:
       5
      /  \
     4    3
   /   \
  1     2
       / \
      7   9

answer:
 [[5], [4,3], [1,2], [7,9]

 */
public class SimpleBFS {
  public static void main(String args[]) {
      TreeNode root = new TreeNode(5);
      root.left = new TreeNode(4);
      root.right = new TreeNode(3);
      root.left.left = new TreeNode(1);
      root.left.right = new TreeNode(2);
      root.left.right.left = new TreeNode(7);
      root.left.right.right = new TreeNode(9);
      SimpleBFS dfs = new SimpleBFS();
      List<List<Integer>> answer = dfs.solve(root);
      System.out.println(answer);

  }

  public List<List<Integer>> solve(TreeNode node) {
      List<List<Integer>> list = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<TreeNode>();
      queue.offer(node);

      while(!queue.isEmpty()) {
          int size = queue.size();
          List<Integer> nodeList = new ArrayList<>();
          for(int i = 0; i< size; i++) {
              TreeNode treeNode = queue.poll();
              nodeList.add(treeNode.value);
              if(treeNode.left != null) {
                  queue.offer(treeNode.left);
              }
              if(treeNode.right != null) {
                  queue.offer(treeNode.right);
              }

          }
          list.add(nodeList);
      }
      return list;
  }

}

