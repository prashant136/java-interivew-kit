// Given a binary tree and a node in the binary tree, find Levelorder successor of the given node.
// That is, the node that appears after the given node in the level order traversal of the tree.

// Consider the following binary tree
//               20
//            /      \
//           10       26
//          /  \     /   \
//        4     18  24    27
//             /  \
//            14   19
//           /  \
//          13  15

// Levelorder traversal of given tree is:
// 20, 10, 26, 4, 18, 24, 27, 14, 19, 13, 15

// Input : 24
// Output : 27

// Input : 4
// Output : 18

class Node {
    constructor(value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

// Utility function to create a new node with given value
function newNode(value) {
    return new Node(value);
}

function levelOrderSuccessor(root, target) {
    if (!root) return -1;

    let queue = [];
    queue.push(root);
    while (queue.length) {
        let levelSize = queue.length;
        for (let i = 0; i < levelSize; i++) {
            const currentNode = queue.shift();
            if (currentNode.left) {
                queue.push(currentNode.left);
            }
            if (currentNode.right) {
                queue.push(currentNode.right);
            }
            // if currentNode === target, then return its successor from queue.
            if (currentNode.value === target) {
                let desiredElement = queue[0];
                return desiredElement;
            }
        }
    }
}

let root = newNode(20);
root.left = newNode(10);
root.left.left = newNode(4);
root.left.right = newNode(18);
root.right = newNode(26);
root.right.left = newNode(24);
root.right.right = newNode(27);
root.left.right.left = newNode(14);
root.left.right.left.left = newNode(13);
root.left.right.left.right = newNode(15);
root.left.right.right = newNode(19);

console.log(levelOrderSuccessor(root, 24));