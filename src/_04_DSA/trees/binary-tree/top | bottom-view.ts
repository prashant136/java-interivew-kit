/**
  - Given a binary tree. The task is to find the top view of the binary tree. 
    The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
  Note: 
    * Return the nodes from the leftmost node to the rightmost node.
    * If two nodes are at the same position (horizontal distance) and are outside the shadow of the tree, 
    * consider the leftmost node only. 
 */

import { TreeNode } from "../tree-utils.ts";

class CustomTreeNode<T extends number> {
    node: TreeNode<T>;
    column: number;
    constructor(node: TreeNode<T>, column: number) {
        this.node = node;
        this.column = column;
    }
}

function topView<T extends number>(root: TreeNode<T> | null): T[] {
    if (!root) return [];

    let result: T[] = [];
    let queue: CustomTreeNode<T>[] = [];
    queue.push(new CustomTreeNode<T>(root, 0)); // Root node with column index 0

    let hashmap = new Map<number, T>(); // Stores column index -> node value

    while (queue.length) {
        const popElement = queue.shift(); // Remove the first element from queue
        if (!popElement) continue;

        const node = popElement.node;
        const col = popElement.column;
        
        // If column index is not yet present in map, add the node's value
        if (!hashmap.has(col)) {
            hashmap.set(col, node.value);
        }
        
        // Add left child to queue with column index -1
        if (node.left) {
            queue.push(new CustomTreeNode<T>(node.left, col - 1));
        }
        
        // Add right child to queue with column index +1
        if (node.right) {
            queue.push(new CustomTreeNode<T>(node.right, col + 1));
        }
    }
    
    // Sort hashmap keys to get the correct top view order
    for (let [key, value] of [...hashmap.entries()].sort((a, b) => a[0] - b[0])) {
        result.push(value);
    }
    return result;
    // OR,
    // return Array.from([...hashmap.entries()].sort((a, b) => a[0]).map(entry => entry[1]));
}
function bottomView<T extends number>(root: TreeNode<T> | null): T[] {
    if (!root) return [];

    let result: T[] = [];
    let queue: CustomTreeNode<T>[] = [];
    queue.push(new CustomTreeNode<T>(root, 0)); // Root node with column index 0

    let hashmap = new Map<number, T>(); // Stores column index -> node value

    while (queue.length) {
        const popElement = queue.shift(); // Remove the first element from queue
        if (!popElement) continue;

        const node = popElement.node;
        const col = popElement.column;
        
        // just update the column
        hashmap.set(col, node.value);
        
        // Add left child to queue with column index -1
        if (node.left) {
            queue.push(new CustomTreeNode<T>(node.left, col - 1));
        }
        
        // Add right child to queue with column index +1
        if (node.right) {
            queue.push(new CustomTreeNode<T>(node.right, col + 1));
        }
    }
    
    // Sort hashmap keys to get the correct top view order
    for (let [key, value] of [...hashmap.entries()].sort((a, b) => a[0] - b[0])) {
        result.push(value);
    }
    return result;
    // OR,
    // return Array.from([...hashmap.entries()].sort((a, b) => a[0]).map(entry => entry[1]));
}

// Create a sample binary tree
//     1
//    / \
//   2   3
//  / \ / \
// 4  5 6  7
let root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right.left = new TreeNode(6);
root.right.right = new TreeNode(7);
console.log(topView(root)); // Expected Output: [4, 2, 1, 3, 7]
console.log(bottomView(root)); // Expected Output: [4, 2, 6, 3, 7]


