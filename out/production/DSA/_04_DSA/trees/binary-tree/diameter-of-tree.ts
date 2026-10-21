import { TreeNode } from "../tree-utils.ts";

class DiameterOfTree<T extends number> {
    // The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
    // * The path may or may not pass through the root.
    // * Length = number of edges on that path.
    // diameter of tree - total edges between 2 leaf nodes. 
    // diameter of node = (left height + right height) for node
    findHeight = (root: TreeNode<T>, result: { value: number }): number => {
        if (!root) return 0;
        let lh = 0;
        let rh = 0;
        if (root.left) lh = this.findHeight(root.left, result);
        if (root.right) rh = this.findHeight(root.right, result);
        result["value"] = Math.max(result["value"], (lh + rh));
        return (1 + Math.max(lh, rh));
    }
    diameterOfBinaryTree = (root: TreeNode<T>): number => {
        if (!root) return 0;
        let result = { value: 0 };   // you can use an object to hold the result value, as objects are passed by reference.
        this.findHeight(root, result);
        return result.value;
    }

}

function diameterOfBinaryTree<T extends number>(root: TreeNode<T>): number {
    let diameter = 0;

    function height(node: TreeNode<T>): number {
        if (!node) return 0;

        let left = height(node.left);
        let right = height(node.right);

        // update diameter at this node
        diameter = Math.max(diameter, left + right);

        // return height of this node
        return 1 + Math.max(left, right);
    }

    height(root);
    return diameter;
}
// const root = new TreeNode(3);
// root.left = new TreeNode(9);
// root.right = new TreeNode(20);
// root.right.left = new TreeNode(15);
// root.right.right = new TreeNode(7);
// const obj = new DiameterOfTree();
// console.log(obj.diameterOfBinaryTree(root));



// facebook
// Given a root of an N-ary tree, you need to compute the length of the diameter of the tree. The diameter of an N-ary
//  tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
//  (Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)
// https://www.youtube.com/watch?v=mPTJyH6Qs4c&list=PLQ7ZAf76c0ZM7k15502F8oSiRSQyBX6R0&index=14 - shashCode

class NaryTreeNode<T> {
    value: T;
    children: NaryTreeNode<T>[];

    constructor(value: T) {
        this.value = value;
        this.children = [];
    }
}
