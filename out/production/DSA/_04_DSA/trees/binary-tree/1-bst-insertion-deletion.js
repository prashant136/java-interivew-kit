class Node {
    constructor(value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BST {
    constructor() {
        this.root = null;
        // this.root = new Node(10);
        // this.root.left = new Node(5);
        // this.root.right = new Node(12);
    }

    // INSERT a value into the BST
    insert(value) {
        this.root = this.insertHelper(this.root, value);
    }

    insertHelper(node, value) {
        // If empty position → create a new node
        if (node === null) {
            return new Node(value);
        }

        // Go left
        if (value < node.value) {
            node.left = this.insertHelper(node.left, value);
        }
        // Go right
        else if (value > node.value) {
            node.right = this.insertHelper(node.right, value);
        }
        
        return node;
    }

    // DELETE a value from BST
    delete(value) {
        this.root = this.deleteHelper(this.root, value);
    }

    deleteHelper(node, value) {
        if (node === null) return null;

        // below two condition for traversing the left and right subtree.
        // once you find the node then start operatation.
        if (value < node.value) {
            // console.log('LEFT')
            node.left = this.deleteHelper(node.left, value);
            // console.log('AFTER LEFT');
        }
        // Search value in right subtree
        else if (value > node.value) {
            // console.log('RIGHT');
            node.right = this.deleteHelper(node.right, value);
            // console.log('AFTER RIGHT');
        }
        // You found the node -> start performing delete operation baased on some condition.
        else {
            // Case 1: Leaf node
            if (!node.left && !node.right) {
                // console.log('LEAF NODE');
                return null;
            }

            // Case 2: One child → replace with child
            if (!node.left) {
                // console.log('!node.left one child case');
                return node.right;
            }
            if (!node.right) {
                // console.log('!node.right one child case');
                return node.left;
            }

            // Case 3: Two children → find inorder successor
            let successor = this.findInorderSuccessor(node.right);
            // console.log('successor', successor)

            // Replace node's value with successor's value
            node.value = successor.value;
            // console.log('node >>>', node);
            

            // Delete successor from right subtree
            node.right = this.deleteHelper(node.right, successor.value);
        }

        // console.log('return node', node);
        
        return node;
    }

    // Find smallest value in a subtree (leftmost node)
    findInorderSuccessor(node) {
        while (node.left !== null) {
            node = node.left;
        }
        return node;
    }

    // Inorder traversal
    inorder(node = this.root) {
        if (node !== null) {
            this.inorder(node.left);
            console.log(node.value);
            this.inorder(node.right);
        }
    }
}

const tree = new BST();

tree.insert(10);
tree.insert(5);
tree.insert(15);
tree.insert(12);
tree.insert(20);
tree.insert(18);
tree.insert(30);

// console.log("Inorder before deletion:");
// tree.inorder();

// tree.delete(12);         // leaf node
// tree.delete(20);         // having one child
tree.delete(15);         // having two child

console.log("Inorder after deleting 15:");
tree.inorder();