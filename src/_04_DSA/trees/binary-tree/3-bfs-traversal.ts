import { TreeNode } from "../tree-utils";

const bfsTraversal = <T>(root: TreeNode<T>|null): T[] => {
    const result: T[] = [];
    if (!root) return result;

    const queue: TreeNode<T>[] = [];
    queue.push(root);

    while (queue.length) {
        const node = queue.shift();
        if (node) {
            result.push(node.value);
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
    }

    return result;
}

// Example usage
const root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(5);
root.right.left = new TreeNode(6);
root.right.right = new TreeNode(7);

console.log(bfsTraversal(root)); // [1, 2, 3, 4, 5, 6, 7] 
