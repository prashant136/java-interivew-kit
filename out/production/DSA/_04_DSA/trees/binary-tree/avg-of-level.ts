// https://leetcode.com/problems/average-of-levels-in-binary-tree/

/***
    Input: root = [3,9,20,null,null,15,7]
    Output: [3.00000,14.50000,11.00000]
    Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
    Hence return [3, 14.5, 11].
    
 */
import { TreeNode } from "../tree-utils.ts";

function averageOfLevels<T extends number>(root: TreeNode<T>): T[] {
    if (!root) return [];

    let queue: TreeNode<T>[] = [];
    let result: T[] = [];
    queue.push(root);

    while (queue.length) {
        let levelSize = queue.length;
        let sum = 0;

        for (let i = 0; i < levelSize; i++) {
            let node = queue.shift();
            if (!node) continue;
            sum += node.value; // Accumulate sum of values at the current level

            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }

        let averageLevels = sum / levelSize as T; // Explicit casting to T
        result.push(averageLevels);
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

console.log(averageOfLevels(root));



