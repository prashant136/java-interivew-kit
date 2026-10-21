import { TreeNode } from "../tree-utils.ts";

// given the root of a binary search tree and an integer k, return true if there exist two elements in the BST
// such that their sum is equal to k, or false otherwise.

// Input: root = [5,3,6,2,4,null,7], k = 9
// Output: true

// Input: root = [5,3,6,2,4,null,7], k = 28
// Output: false

/** 
    Approach 1: Using DFS + HashSet
        👉 Use a Set to store visited values.
        👉 Traverse the BST using DFS.
        👉 For each node:
            ● Check if (k - node.value) exists in the set.
            ● If yes, return true (since a pair exists).
            ● Otherwise, add (node.value) to the set and continue traversal.
        
    Time Complexity: O(N)
    Space Complexity: O(N).
 */
function findTargetDFS<T extends number>(root: TreeNode<T> | null, k: number): boolean {
    if (!root) return false;
    let set = new Set<number>();

    function dfs(node: TreeNode<T> | null): boolean {
        if (!node) return false;

        if (set.has(k - node.value)) return true;
        set.add(node.value);

        const left = dfs(node.left);
        const right = dfs(node.right);
        return left || right;
    }
    return dfs(root);
}

/**
    Approach 2: Using BFS (Level Order Traversal) + HashSet
        👉 Instead of DFS, we can use BFS (Queue-based Level Order Traversal) to find two numbers whose sum equals k.
        👉 Use a queue for BFS traversal.
        👉 Use a Set to store visited values.
        👉 For each node:
            ● If (k - node.value) is found in the set, return true.
            ● Otherwise, add node.value to the set.
            ● Push left and right children into the queue.
            ● If traversal completes and no pair is found, return false.
    
    Time Complexity: O(N)
    Space Complexity: O(N)
*/
function findTargetBFS<T extends number>(root: TreeNode<T> | null, k: number): boolean {
    if (!root) return false;

    let set = new Set<number>();
    let queue: TreeNode<T>[] = [root];

    while (queue.length) {
        let node = queue.shift();
        if (!node) continue;

        if (set.has(k - node.value)) return true;
        set.add(node.value);

        if (node.left) queue.push(node.left);
        if (node.right) queue.push(node.right);
    }

    return false;
}

/**
    Approach 3: Using Inorder Traversal + Two Pointers (Optimal for BST)
        # Perform inorder traversal to get a sorted array.
        # Use two-pointer technique to find if two elements sum to k.
    
    Time Complexity: O(N)
    Space Complexity: O(N)
 */
function findTargetTwoPointers<T extends number>(root: TreeNode<T> | null, k: number): boolean {
    let nums: number[] = [];

    function inorder(node: TreeNode<T> | null) {
        if (!node) return;
        inorder(node.left);
        nums.push(node.value);
        inorder(node.right);
    }

    inorder(root);

    let left = 0, right = nums.length - 1;
    while (left < right) {
        let sum = nums[left] + nums[right];
        if (sum === k) return true;
        else if (sum < k) left++;
        else right--;
    }

    return false;
}

let root = new TreeNode(5);
root.left = new TreeNode(3);
root.right = new TreeNode(6);
root.left.left = new TreeNode(2);
root.left.right = new TreeNode(4);
root.right.right = new TreeNode(7);

console.log(findTargetDFS(root, 9));
console.log(findTargetBFS(root, 10));
console.log(findTargetTwoPointers(root, 10));


