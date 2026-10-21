import { TreeNode } from "../tree-utils.ts";

class HeightOfTree<T extends number> {

    maxDepthBFS = (root: TreeNode<T>): number => {
        if (!root) return 0;
        let queue: TreeNode<T>[] = [];
        let level = 0

        queue.push(root);
        while (queue.length) {
            let size = queue.length;    // this size is for level detection. when size is zero means we completed a level. 
            while (size > 0) {
                let node = queue.shift();
                size--;
                if (node && node.left) queue.push(node.left);
                if (node && node.right) queue.push(node.right);
            }
            // if size is ZERO -> we have crossed a complete level...
            if (queue.length) level++; 
        }
        return level + 1;
    }

    maxDepthDFS = (root: TreeNode<T>) => {
        if (!root) return 0;

        let lh = 0;
        let rh = 0;
        if (root.left) lh = this.maxDepthDFS(root.left);
        if (root.right) rh = this.maxDepthDFS(root.right);

        return (1 + Math.max(lh, rh));
    }
}

const root = new TreeNode(3);
root.left = new TreeNode(9);
root.right = new TreeNode(20);
root.right.left = new TreeNode(15);
root.right.right = new TreeNode(7);

const obj = new HeightOfTree();
console.log(obj.maxDepthBFS(root));
// console.log(obj.maxDepthDFS(root));
