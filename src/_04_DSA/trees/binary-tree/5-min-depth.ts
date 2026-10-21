import { TreeNode } from "../tree-utils.ts";

function minDepth(root: TreeNode) {
    if (root == null) return 0;

    // leaf node
    if (root.left == null && root.right == null) return 1;

    const left = minDepth(root.left);
    const right = minDepth(root.right);

    // if one side is null, take the other side
    if (root.left == null) return 1 + right;
    if (root.right == null) return 1 + left;

    return 1 + Math.min(left, right);
}


const root = new TreeNode(3);
root.left = new TreeNode(9);
root.right = new TreeNode(20);
root.right.left = new TreeNode(15);
root.right.right = new TreeNode(7);

console.log(minDepth(root));