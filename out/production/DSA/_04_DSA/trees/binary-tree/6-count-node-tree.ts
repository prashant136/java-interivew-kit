import { TreeNode } from "../tree-utils.ts";

const countNodes = function(root: TreeNode): number {
    if(!root) return 0;
    
    return 1 + countNodes(root.left) + countNodes(root.right);
};

const sumNodes = function(root: TreeNode): number {
    if(!root) return 0;
    
    return root.value + sumNodes(root.left) + sumNodes(root.right);
};

const root = new TreeNode(10);
root.left = new TreeNode(5);
root.left.left = new TreeNode(2);
root.right = new TreeNode(20);
root.right.left = new TreeNode(15);
root.right.right = new TreeNode(25);

console.log(countNodes(root));
console.log(sumNodes(root));