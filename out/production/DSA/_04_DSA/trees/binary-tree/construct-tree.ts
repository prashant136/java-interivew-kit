// 👉 --unstable-sloppy-imports is a flag in Deno that allows importing local files without specifying their file extensions (e.g., .ts, .js).
// import { TreeNode } from "../tree-utils.ts";  // deno construct-tree.ts
// import { TreeNode } from "../tree-utils";   // deno run --unstable-sloppy-imports construct-tree.ts
import { TreeNode } from "../tree-utils.ts";

class ConstructTree<T extends number> {
    /**
     * Builds a binary tree from preorder and inorder traversal arrays.
     * @param preOrder - The preorder traversal of the tree.
     * @param inOrder - The inorder traversal of the tree.
     * @returns The root of the constructed binary tree.
     */
    buildTreeWithPreorderInorder(
        preOrder: T[],
        inOrder: T[]
    ): TreeNode<T> | null {
        // Base case: If preorder or inorder is empty, return null
        if (preOrder.length === 0 || inOrder.length === 0) {
            return null;
        }

        // The first element of preorder is the root of the tree
        const rootValue = preOrder[0];
        const root = new TreeNode<T>(rootValue);

        // Find the index of the root in inorder to divide left and right subtrees
        const rootIndex = inOrder.indexOf(rootValue);

        // Construct the left and right subtrees recursively
        const leftInOrder = inOrder.slice(0, rootIndex);
        const rightInOrder = inOrder.slice(rootIndex + 1);

        const leftPreOrder = preOrder.slice(1, leftInOrder.length + 1);
        const rightPreOrder = preOrder.slice(leftInOrder.length + 1);

        root.left = this.buildTreeWithPreorderInorder(leftPreOrder, leftInOrder);
        root.right = this.buildTreeWithPreorderInorder(rightPreOrder, rightInOrder);

        return root;
    }

    // 👉 approch 2 -
    constructTree(
        preOrder: T[],
        inOrder: T[],
        hashmap: Map<T, number>,
        preStartIndex: number,
        preEndIndex: number,
        inStartIndex: number,
        inEndIndex: number
    ): TreeNode<T> | null {
        if (preStartIndex > preEndIndex || inStartIndex > inEndIndex) {
            return null;
        }
        const rootData = preOrder[preStartIndex];
        const rootIndex = hashmap.get(rootData);
        const root = new TreeNode(rootData);

        // TypeScript fix: Ensure `rootIndex` is not undefined
        if (rootIndex === undefined) {
            throw new Error(
                `Invalid traversal: Node value ${rootData} not found in inOrder traversal.`
            );
        }

        const leftTreeSize = rootIndex - inStartIndex;
        const rightTreeSize = inEndIndex - rootIndex;

        root.left = this.constructTree(
            preOrder,
            inOrder,
            hashmap,
            preStartIndex + 1,
            preStartIndex + leftTreeSize,
            inStartIndex,
            rootIndex - 1
        );
        root.right = this.constructTree(
            preOrder,
            inOrder,
            hashmap,
            preStartIndex + leftTreeSize + 1,
            preStartIndex + leftTreeSize + rightTreeSize,
            rootIndex + 1,
            inEndIndex
        );
        return root;
    }

    buildTreeWithPreorderInorderII(
        preOrder: T[],
        inOrder: T[]
    ): TreeNode<T> | null {
        let hashmap = new Map<T, number>();
        for (let i = 0; i < inOrder.length; i++) {
            hashmap.set(inOrder[i], i); // has the value with index
        }
        let root = this.constructTree(
            preOrder,
            inOrder,
            hashmap,
            0,
            preOrder.length - 1,
            0,
            inOrder.length - 1
        );
        return root;
    }
}

const preOrder = [3, 9, 20, 15, 7];
const inOrder = [9, 3, 15, 20, 7];

const obj = new ConstructTree<number>();
console.log(obj.buildTreeWithPreorderInorder(preOrder, inOrder));
console.log(obj.buildTreeWithPreorderInorderII(preOrder, inOrder));

// It is generally recommended to use the number type instead of the Number object in TypeScript,
//  as it is a more lightweight and efficient way of working with numeric values
