// Input: root = [1,2,3,null,5,null,4]
// Output: [1,3,4]

// Input: root = [1,null,3]
// Output: [1,3]

// Input: root = []
// Output: []
import { TreeNode } from "../tree-utils.ts"

function rightSideView<T extends number>(root: TreeNode<T>) {
    if (!root) return [];  // If the tree is empty, return an empty array.

    let queue: TreeNode<T>[] = []; // Queue for BFS traversal
    let result: T[] = []; // Stores the right-side view nodes
    queue.push(root); // Start with the root node

    while (queue.length) { 
        let levelSize = queue.length; // Number of nodes at the current level
        while (levelSize > 0) {
            let popElement = queue.shift(); // Remove the front node from the queue
            if (popElement) {
                // Add left child first
                if (popElement.left) {
                    queue.push(popElement.left);
                }
                // Add right child next
                if (popElement.right) {
                    queue.push(popElement.right);
                }
                levelSize--;
                // When `levelSize === 0`, we are at the last node of this level (rightmost)
                if (levelSize === 0) {
                    result.push(popElement.value);
                }
            }
        }
    }
    return result;
}


function leftSideView<T extends number>(root: TreeNode<T>) {
    if (!root) return [];  // If the tree is empty, return an empty array.

    let queue: TreeNode<T>[] = []; // Queue for BFS traversal
    let result: T[] = []; // Stores the left-side view nodes
    queue.push(root); // Start with the root node

    while (queue.length) {
        let levelSize = queue.length; // Number of nodes at the current level
        for (let i = 0; i < levelSize; i++) {
            let popElement = queue.shift(); // Remove the front node from the queue
            if (popElement) {
                // The first node encountered at each level is stored in `result`
                if (i === 0) {
                    result.push(popElement.value);
                }
                // Push left child first to preserve the left view order
                if (popElement.left) {
                    queue.push(popElement.left);
                }
                // Push right child next
                if (popElement.right) {
                    queue.push(popElement.right);
                }
            }
        }
    }
    return result;
}


const root = new TreeNode(10)
root.left = new TreeNode(2)
root.right = new TreeNode(3)
root.left.left = new TreeNode(7)
root.left.right = new TreeNode(8)
root.right.right = new TreeNode(15)
root.right.left = new TreeNode(12)
root.right.right.left = new TreeNode(14)

console.log(rightSideView(root));
console.log(leftSideView(root));
