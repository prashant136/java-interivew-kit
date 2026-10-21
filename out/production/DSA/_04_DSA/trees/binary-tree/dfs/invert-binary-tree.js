// Input: root = [4,2,7,1,3,6,9]
// Output: [4,7,2,9,6,3,1]

//                 4
//              /    \
//            2        7
//          /  \     /   \
//        1     3   6     9
//                 |
//                 |
//            turned into
//                 |
//                 |
//                 4
//              /    \
//            7       2
//          /  \     /   \
//         9    6   3     1

// Input: root = [2,1,3]
// Output: [2,3,1]
//                 2
//              /    \
//             1      3
//                 |
//                 |
//            turned into
//                 |
//                 |
//                 2
//              /    \
//             3      1

class Node {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

function invertTree(root) {
    if (root === null) {
        return null;
    }

    let leftNode = invertTree(root.left);
    let rightNode = invertTree(root.right);

    root.left = rightNode;
    root.right = leftNode;
    return root;
}

let root = null;
root = new Node(4);
root.left = new Node(2);
root.right = new Node(7);
root.left.left = new Node(1);
root.left.right = new Node(3);
root.right.left = new Node(6);
root.right.right = new Node(9);
let ans = invertTree(root);
console.log(ans);
