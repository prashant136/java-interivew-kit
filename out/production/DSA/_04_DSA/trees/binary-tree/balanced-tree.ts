import { TreeNode } from "../tree-utils";

/**
 * 
 * A binary tree is height-balanced if:
    - The left and right subtrees of every node differ in height by at most 1.
    - The left subtree is balanced.
    - The right subtree is balanced.
 */
function isBalanced<T>(root: TreeNode<T> | null): boolean {
    function height(node: TreeNode<T> | null): number {
        if (!node) return 0; // Base case: empty tree has height 0

        let leftHeight = height(node.left);
        let rightHeight = height(node.right);

        if (leftHeight === -1 || rightHeight === -1) return -1;
        // Diffrence b/w left and right subtrees of every node is > 1 then return -1. 
        // hence node is unBalanced
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1; // Height of current node
    }
    // if height is -1 then unbalanced else balanced tree.
    return height(root) !== -1;
}
// Example usage
const root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right.left = new TreeNode(6);
root.right.right = new TreeNode(7);

console.log(isBalanced(root));