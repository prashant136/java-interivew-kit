import { TreeNode } from "../tree-utils.ts";

function hasPathSum(root, sum) {
    if (!root) return false;

    sum-= root.value;

    // leaf condition
    if (root.left === null && root.right === null) {
        return sum === 0 ? true: false;
    }

    hasPathSum(root.left, sum) || hasPathSum(root.right, sum)
}

const root = new TreeNode(10);
root.left = new TreeNode(2);
root.right = new TreeNode(8);
root.left.left = new TreeNode(1);
// root.left.left = new TreeNode(3);
// root.left.right = new TreeNode(5);
// root.right.left = new TreeNode(2);

const sum = 12;
console.log(hasPathSum(root, sum));
