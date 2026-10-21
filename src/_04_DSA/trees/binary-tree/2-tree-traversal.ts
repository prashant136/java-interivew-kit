import { TreeNode } from "../tree-utils.ts";

// ---------------------------------------- In-Order Traversal  ------------------------------------------------
const inOrderTraversal = <T>(root: TreeNode<T> | null, result: T[] = []): T[] => {
    if (root) {
        inOrderTraversal(root.left, result)
        result.push(root.value);
        inOrderTraversal(root.right, result);
    }
    return result;
}
// using $04DSA.stack - left -> root -> right
const inOrderTraversalStack = <T>(root: TreeNode<T>): T[] => {
    const result: T[] = [];
    const stack: TreeNode<T>[] = [];
    let current: TreeNode<T> | null = root;

    while (current !== null || stack.length > 0) {
        // Traverse to the leftmost node
        while (current !== null) {
            stack.push(current);
            current = current.left;
        }

        const node = stack.pop();
        if (node) {
            result.push(node.value); // Push the node's value to the result array
            current = node.right;   // Move to the right subtree
        }
    }
    return result;
}

// ---------------------------------------- Pre-Order Traversal --------------------------------------------
const preOrderTraversal = <T>(root: TreeNode<T> | null, result: T[] = []): T[] => {
    if (root) {
        result.push(root.value);
        preOrderTraversal(root.left, result)
        preOrderTraversal(root.right, result);
    }
    return result;
};
// using $04DSA.stack - root -> left -> right
const preOrderTraversalStack = <T>(root: TreeNode<T>): T[] => {
    let result: T[] = [];
    let stack: TreeNode<T>[] = [];
    if (!root) return result;

    stack.push(root);
    while (stack.length) {
        const popElement = stack.pop();
        if (popElement) {
            result.push(popElement?.value);
            if (popElement?.right) stack.push(popElement.right);
            if (popElement?.left) stack.push(popElement.left);
        }
    }
    return result;
};

// ----------------------------------------- Post-Order Traversal ------------------------------------------
const postOrderTraversal = <T>(root: TreeNode<T> | null, result: T[] = []): T[] => {
    if (root) {
        postOrderTraversal(root.left, result);
        postOrderTraversal(root.right, result);
        result.push(root.value);
    }
    return result;
};
// using $04DSA.stack - left -> right -> root
const postOrderTraversalStack = <T>(root: TreeNode<T>): T[] => {
    let result: T[] = [];
    let stack: TreeNode<T>[] = [];
    let lastVisited: TreeNode<T> | null = null;
    let current: TreeNode<T> | null = root;

    while (current !== null || stack.length > 0) {
        // Step 1: Go left as much as possible
        while (current) {
            stack.push(current);
            current = current.left;
        }

        let top = stack[stack.length - 1];

        // Step 2: If right child exists and not visited → go right
        if (top.right && lastVisited !== top.right) {
            current = top.right;
        } 
        // Step 3: Otherwise → process node
        else {
            result.push(top.value);
            lastVisited = stack.pop()!;
        }
    }

    return result;
};


/**
    - How It Works:
        👉 Two-Stack Approach:
        👉 Stack 1 -> reverse pre-order traversal (Root → Right → Left).
        👉 Nodes are pushed into Stack 2 during this traversal.
        👉 Stack 2 -> Then reverse the result → Left → Right → Root.
 */
const postOrderTraversalTwoStack = <T>(root: TreeNode<T> | null): T[] => {
    const result: T[] = [];
    if (!root) return result;

    const stack1: TreeNode<T>[] = [];
    const stack2: TreeNode<T>[] = [];

    stack1.push(root);

    // First $04DSA.stack is used for traversal
    while (stack1.length > 0) {
        const node = stack1.pop();
        if (node) {
            stack2.push(node); // Push to second $04DSA.stack
            if (node.left) stack1.push(node.left);   // Push left child
            if (node.right) stack1.push(node.right); // Push right child
        }
    }

    // Second $04DSA.stack gives post-order traversal
    while (stack2.length > 0) {
        const node = stack2.pop();
        if (node) result.push(node.value);
    }

    return result;
};



const root = new TreeNode(10);
root.left = new TreeNode(6);
root.right = new TreeNode(15);
root.left.left = new TreeNode(4);
root.left.right = new TreeNode(8);
root.left.right.left = new TreeNode(7);
root.right.right = new TreeNode(20);


            //         10
            //      /     \
            //     6       15
            //    /  \       \
            //   4    8      20
            //      /
            //     20 
// Perform DFS Traversals
console.log("In-Order Traversal:", inOrderTraversal(root));
console.log("Pre-Order Traversal:", preOrderTraversal(root));
console.log("Post-Order Traversal:", postOrderTraversal(root));
console.log("In-Order Traversal using $04DSA.stack:", inOrderTraversalStack(root));
console.log("Pre-Order Traversal using $04DSA.stack:", preOrderTraversalStack(root));
console.log("Post-Order Traversal using $04DSA.stack:", postOrderTraversalStack(root));
console.log("Post-Order Traversal using two $04DSA.stack:", postOrderTraversalTwoStack(root));
