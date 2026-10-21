// Input: root = [3,9,20,null,null,15,7]
// Output: [[3],[20,9],[15,7]]
// Example 2:

// Input: root = [1]
// Output: [[1]]
// Example 3:

// Input: root = []
// Output: []

// Consider the following binary tree
//                 1
//              /    \
//            2        3
//          /  \     /   \
//        4     5   6     7
//      /  \      /  \
//     8   9     10  11

import { TreeNode } from "../tree-utils.ts";

function zigzagLevelOrder<T extends number>(root: TreeNode<T>): T[][] {
    if (!root) return [];

    let result: T[][] = [];
    let queue: TreeNode<T>[] = [root];
    let leftToRight = true; // Flag to alternate directions

    while (queue.length) {
        let levelSize = queue.length;
        let levelNodes: T[] = [];

        for (let i = 0; i < levelSize; i++) {
            let node = queue.shift();
            if (node) {
                if (leftToRight) {
                    levelNodes.push(node.value); // Normal order
                } else {
                    levelNodes.unshift(node.value); // Reverse order
                }
                if (node.left) queue.push(node.left);
                if (node.right) queue.push(node.right);
            }
        }

        result.push(levelNodes);
        leftToRight = !leftToRight; // Switch direction
    }

    return result;
}

function zigzagLevelOrderDeque<T extends number>(root: TreeNode<T>): T[][] {
    if (!root) return [];

    let result: T[][] = [];
    let deque: TreeNode<T>[] = []; // Deque for level order traversal
    let leftToRight = true; // Flag to alternate direction

    deque.push(root);

    while (deque.length) {
        let levelSize = deque.length;
        let levelNodes: T[] = [];

        for (let i = 0; i < levelSize; i++) {
            if (leftToRight) {
                let node = deque.shift(); // Remove from front
                if (node) {
                    levelNodes.push(node.value);
                    // Push left first, then right
                    if (node.left) deque.push(node.left);
                    if (node.right) deque.push(node.right);
                }
            } else {
                let node = deque.pop(); // Remove from back
                if (node) {
                    levelNodes.push(node.value);
                    // Push right first, then left (reversed order)
                    if (node.right) deque.unshift(node.right);
                    if (node.left) deque.unshift(node.left);
                }
            }
        }

        result.push(levelNodes);
        leftToRight = !leftToRight; // Switch direction
    }

    return result;
}

let root = new TreeNode(1);
root.left = new TreeNode(2);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right = new TreeNode(3);
root.right.left = new TreeNode(6);
root.right.right = new TreeNode(7);
root.left.left.left = new TreeNode(8);
root.left.left.right = new TreeNode(9);
root.right.left.left = new TreeNode(10);
root.right.left.right = new TreeNode(11);
console.log(zigzagLevelOrder(root));      // Output: [[1], [3, 2], [4, 5, 6, 7]]
console.log(zigzagLevelOrderDeque(root)); // Output: [[1], [3, 2], [4, 5, 6, 7]]
