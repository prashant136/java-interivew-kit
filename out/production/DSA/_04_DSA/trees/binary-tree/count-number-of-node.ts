import { TreeNode } from "../tree-utils.ts";

/***
 * 
    You are given the root of a complete binary tree. Your task is to find the count of nodes.
    A complete binary tree is a binary tree whose, all levels except the last one are completely filled,
    the last level may or may not be completely filled and Nodes in the last level are as left as possible.

    Design an algorithm that runs better than O(n).
 */
// Problem Statement:

// Time complexity - O(N), space complexity - height of tree
const countNodes = <T extends number>(root: TreeNode<T>, result: { value: number }): number => {
    if (!root) return 0;
    let leftNodes = 0;
    let rightNodes = 0;
    if (root.left) leftNodes = countNodes(root.left, result);
    if (root.right) rightNodes = countNodes(root.right, result);
    const totalNodes = 1 + leftNodes + rightNodes;
    result["value"] = totalNodes;
    return totalNodes;
}
const totalNodesBinaryTree = <T extends number>(root: TreeNode<T>): number => {
    let result = { value: 0 };   // you can use an object to hold the result value, as objects are passed by reference.
    countNodes(root, result);
    return result.value;
}

// Design an algorithm that runs better than O(n).
const findLeftHeight = <T extends number>(root: TreeNode<T>): number => {
    let count = 1;
    while(root.left) {
        count++;
        root = root.left;
    }
    return count;
} 
const findRightHeight = <T extends number>(root: TreeNode<T>): number => {
    let count = 1;
    while(root.right) {
        count++;
        root = root.right;
    }
    return count;
} 
// 🍀 In a complete binary tree - total nodes = (2^n-1), n is level
const totalNodesTree = <T extends number>(root: TreeNode<T>): number => {
    if(!root) return 0;
    const leftHeight = findLeftHeight(root);
    const rightHeight = findRightHeight(root);
    if(leftHeight === rightHeight) {
        return Math.pow(2, leftHeight)-1;
    }
    return 1 + totalNodesTree(root.left) + totalNodesTree(root.right);
}


const root = new TreeNode(3);
root.left = new TreeNode(9);
root.right = new TreeNode(20);
root.right.left = new TreeNode(15);
root.right.right = new TreeNode(7);;
console.log(totalNodesBinaryTree(root));
console.log(totalNodesTree(root));