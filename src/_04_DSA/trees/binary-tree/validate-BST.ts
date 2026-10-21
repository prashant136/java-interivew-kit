import { TreeNode } from '../tree-utils.ts';

class ValidBinarySearchTree<T extends number> {

    // Using specified range of Min and Max Values – O(n) Time and O(h) Space
    validate<T>(node: TreeNode<T> | null, min: T, max: T): boolean {
        if (node === null) {
            return true;
        }
        // If the current node's data is not in the valid range, return false
        if (node.value < min || node.value > max) {
            return false;
        }

        const leftTree = this.validate(node.left, min, node.value);
        const rightTree = this.validate(node.right, node.value, max);
        return leftTree && rightTree;
    }
    // BFS
    isValidBFS(root: TreeNode<number> | null): boolean {
        if (!root) return true;
    
        let queue: [TreeNode<number>, number, number][] = [];
        queue.push([root, -Infinity, Infinity]); // Start with the whole valid range
    
        while (queue.length) {
            let [node, min, max] = queue.shift()!;
    
            // If node value is out of the valid range, return false
            if (node.value <= min || node.value >= max) return false;
    
            // Left child must be within (min, node.value)
            if (node.left) queue.push([node.left, min, node.value]);
    
            // Right child must be within (node.value, max)
            if (node.right) queue.push([node.right, node.value, max]);
        }
    
        return true; // If all nodes are valid, it's a BST
    }
    
    // Using Inorder Traversal – O(n) Time and O(h) Space
    /**
     * The idea is to use inorder traversal of a binary search tree,
     * in which the output values are sorted in ascending order. After generating
     * the inorder traversal of the given binary tree, we can check if the values are sorted or not.
     */
    validateWithInorder<T>(node: TreeNode<T> | null, min: T, max: T) {

    }

}

const root = new TreeNode(10);
root.left = new TreeNode(5);
root.right = new TreeNode(15);
root.left.right = new TreeNode(7);
root.right.left = new TreeNode(12);
root.right.right = new TreeNode(20);

const obj = new ValidBinarySearchTree();
console.log(obj.validate(root, -Infinity, Infinity));