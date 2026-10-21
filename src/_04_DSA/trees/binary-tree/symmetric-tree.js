// Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

//                 1
//              /  |  \
//            2    |    2
//          /  \   |  /   \
//        3     4  | 4     3
// Input: root = [1,2,2,3,4,4,3]
// Output: true

//                 1
//              /  |  \
//            2    |    2
//             \   |     \
//              3  |      3
// Input: root = [1,2,2,null,3,null,3]
// Output: false

class Node {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

function isSymmetric(root) {
    let queue = [];
    queue.push(root.left);
    queue.push(root.right);

    while (queue.length) {
        let left = queue.shift();
        let right = queue.shift();

        // both childs are null
        if (left === null && right === null) {
            continue;
        }

        // anyone of child is null
        if (left === null || right === null) {
            return false;
        }

        // both child is diffrent
        if (left.val !== right.val) {
            return false;
        }

        // push to queue
        queue.push(left.left);
        queue.push(right.right);
        queue.push(left.right);
        queue.push(right.left);
    }
    return true;
}

let root = null;
root = new Node(1);
root.left = new Node(2);
root.right = new Node(2);
root.left.left = new Node(3);
root.left.right = new Node(4);
root.right.left = new Node(4);
root.right.right = new Node(3);
root.left.left.left = new Node(5);
root.left.left.right = new Node(6);
root.right.right.left = new Node(6);
root.right.right.right = new Node(5);

console.log(isSymmetric(root));
