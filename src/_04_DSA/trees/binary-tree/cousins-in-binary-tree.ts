// Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y,
// return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

// Two nodes of a binary tree are cousins if they have the same depth with different parents.

//                 1
//              /    \
//            2        3
//          /
//        4
// Input: root = [1,2,3,4], x = 4, y = 3
// Output: false

//                 1
//              /    \
//            2       3
//             \       \
//              4       5
// Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
// Output: true

//                 1
//              /    \
//             2      3
//              \
//               4
// Input: root = [1,2,3,null,4], x = 2, y = 3
// Output: false
// ---------------------------------------------------------------------------------------------------

import { TreeNode } from "../tree-utils.ts";

class BinaryTreeGeneral<T> {
    /**
     * Finds a node with the given value.
     * @param root - The current node.
     * @param val - The value to search for.
     * @returns The TreeNode if found, otherwise null.
     */
    findNode(root: TreeNode<T> | null, val: T): TreeNode<T> | null {
        if (root === null) {
            return null;
        }
        if (root.value === val) {
            return root;
        }
        // Recursive search in left and right subtrees
        return this.findNode(root.left, val) || this.findNode(root.right, val);
    }
    /**
     * Checks if two nodes are siblings (have the same parent).
     * @param root - The root node.
     * @param val1 - First node.
     * @param val2 - Second node.
     * @returns True if they are siblings, otherwise false.
     */
    isSibling<U extends TreeNode<T> | null>(root: U, val1: U, val2: U): boolean | null {
        if (root === null) {
            return null;
        }

        if (
            (root.left === val1 && root.right === val2) ||
            (root.right === val1 && root.left === val2)
        ) {
            return true;
        }
        return this.isSibling(root.left, val1, val2) || this.isSibling(root.right, val1, val2);

        // return (
        //     (node.left === x && node.right === y) ||
        //     (node.right === x && node.left === y) ||
        //     isSibling(node.left, x, y) ||
        //     isSibling(node.right, x, y)
        // );
    }
    /**
     * Finds the level (depth) of a node with a given value.
     * @param node - The current node.
     * @param value - The value to search for.
     * @param level - The current level (depth).
     * @returns The level if found, otherwise 0.
     */
    findLevel(node: TreeNode<T> | null, value: T, level: number): number {
        if (!node) {
            return 0;
        }
        if (node.value === value) {
            return level;
        }

        // Search in left and right subtrees
        return (
            this.findLevel(node.left, value, level + 1) ||
            this.findLevel(node.right, value, level + 1)
        );
    }
    /**
     * 👋 both are in same level but they should not belong to same parent(they ain't sibling)
     * Determines if two nodes are cousins in a binary tree.
     * Two nodes are cousins if they are at the same level but have different parents.
     * @param root - The root of the binary tree.
     * @param x - Value of the first node.
     * @param y - Value of the second node.
     * @returns True if they are cousins, otherwise false.
    */
    isCousins(root: TreeNode<T> | null, x: T, y: T): boolean {
        let xx = this.findNode(root, x);
        let yy = this.findNode(root, y);

        return (
            this.findLevel(root, x, 0) === this.findLevel(root, y, 0) &&
            !this.isSibling(root, xx, yy)
        );
    }
}

const obj = new BinaryTreeGeneral();
const root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right.right = new TreeNode(6);

// console.log(obj.findNode(root, 2));
// console.log(obj.isSibling(root, root.left.left, root.left.right));
// console.log(obj.findLevel(root, 5, 0));
console.log(obj.isCousins(root, 5, 6));
