// The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
// This path may or may not pass through the root. The length of a path between two nodes is
// represented by the number of edges between them.

// Input: root = [1,2,3,4,5]
// Output: 3
// Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

// Input: root = [1,2]
// Output: 1

class Node {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

let diameter = 0;
function diameterOfBinaryTree(root) {
    height(root);
    return diameter;
}
function height(root) {
    if (root === null) {
        return 0;
    }
    let leftHeight = height(root.left);
    let rightHeight = height(root.right);

    // diameter (height of left subtree + heigth of right subtree + node itself)
    let dia = leftHeight + rightHeight + 1;
    diameter = Math.max(diameter, dia);
    
    // height of perticular node = maximum of (height of left subtree, height of right subtree) + node iteself
    return Math.max(leftHeight, rightHeight) + 1;
}

