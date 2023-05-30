package io.windfree.algorithm.tree;



import java.util.List;

/*
 input:
        5
       /  \
      4    3
    /   \
   1     2
        / \
       7   9
in order traverse
 answer:
 1 4 7 2 9 5 3
* 참고
inorder : left node -> root -> right node
preorder : root -> lefet -> right
postorder : left -> right -> root
  */
public class InOrderTraverse {
    public static void main(String args[]) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(9);
        InOrderTraverse dfs = new InOrderTraverse();
        dfs.inOrderTraverse(root);
        //System.out.println(answer);

    }

    public void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.left);
            Print(root);
            inOrderTraverse(root.right);
        }
    }

    public  void Print(TreeNode node) {
        System.out.print(node.value + " ");
    }


}


