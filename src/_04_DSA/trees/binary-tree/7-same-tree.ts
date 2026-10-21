import { TreeNode } from "../tree-utils.ts";

const isSameTree = (p, q): boolean => {
    if (p === null && q === null) return true;
    if(p === null || q === null) return false;

    if(p.value !== q.value) return false;

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
};

const p = new TreeNode(10);
p.left = new TreeNode(8);
p.right = new TreeNode(12);
p.left.left = new TreeNode(4);

const q = new TreeNode(10);
q.left = new TreeNode(18);
q.right = new TreeNode(12);
q.left.left = new TreeNode(4);

console.log(isSameTree(p, q));