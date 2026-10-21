// 👋 if tree is binary search tree. then in-order traversal of the binary tree gives
//  sorted array in ascending order.
// function validateBST(root) {
//     const values = [];

//     function inorderTraversal(node) {
//         if (node === null) return;

//         inorderTraversal(node.left);
//         values.push(node.val);
//         inorderTraversal(node.right);
//     }
//     inorderTraversal(root);

//     for (let i = 0; i < values.length - 1; i++) {
//         if (values[i] > values[i + 1]) {
//             return false;
//         }
//     }
//     return true;
// }

// Recursive approach with range constraints
class Node {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

function helper(node, low, high) {
    if (node == null) {
        return true;
    }

    if (low != null && node.val <= low) {
        return false;
    }
    if (high != null && node.val >= high) {
        return false;
    }

    const leftAns = helper(node.left, low, node.val);
    const rightAns = helper(node.right, node.val, high);
    return leftAns && rightAns;
}

function validateBST(root) {
    return helper(root, null, null);
}
let root = null;
root = new Node(6);
root.left = new Node(4);
root.left.left = new Node(2);
root.left.right = new Node(5);
root.right = new Node(10);
root.right.left = new Node(8);
root.right.right = new Node(20);
root.right.right.right = new Node(31);
console.log(validateBST(root));
