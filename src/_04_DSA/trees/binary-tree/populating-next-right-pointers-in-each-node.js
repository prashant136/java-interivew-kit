// You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
// The binary tree has the following definition:
// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }

//                 1
//              /    \
//            2        3
//          /  \     /   \
//        4     5   6     7

//                  |
//                  |
//            turned into
//                  |
//                  |

//                 1 --> null
//              /    \
//            2  -->  3 --> null
//          /  \     /   \
//         4 -> 5 -> 6 -> 7 --> null

// Input: root = [1,2,3,4,5,6,7]
// Output: [1,#,2,3,#,4,5,6,7,#]
// Explanation: Given the above perfect binary tree(Figure A), your function should populate each next pointer to point to its next right node,
//     just like in Figure B.The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

// Input: root = []
// Output: []

// Solution in constant space
function connect(root) {
    if (root === null) {
        return null;
    }

    let leftMost = root;
    while (leftMost.left != null) {
        let current = leftMost;
        while (current != null) {
            current.left.next = current.right;
            if (current.next != null) {
                current.right.next = current.next.left;
            }
            current = current.next;
        }
        leftMost = leftMost.left;
    }
    return root;
}
