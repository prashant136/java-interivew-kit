class Node {
    constructor(startInterval, endInterval) {
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.data = 0;
        this.left = null;
        this.right = null;
    }
}

class SegmentTree {
    constructTree(arr, start, end) {
        if (start === end) {
            let leaf = new Node(start, end);
            leaf.data = arr[start];
            return leaf;
        }

        let mid = Math.floor((start + end) / 2);
        // Create a new node with the indices
        let node = new Node(start, end);
        node.left = this.constructTree(arr, start, mid);
        node.right = this.constructTree(arr, mid + 1, end);
        node.data = node.left.data + node.right.data;
        return node;
    }

    display(node) {
        let str = "";

        if (node.left != null) {
            str =
                str +
                "Interval=[" +
                node.left.startInterval +
                "-" +
                node.left.endInterval +
                "] and data: " +
                node.left.data +
                " => ";
        } else {
            str = str + "No left child";
        }

        // for current node
        str =
            str +
            "Interval=[" +
            node.startInterval +
            "-" +
            node.endInterval +
            "] and data: " +
            node.data +
            " <= ";

        if (node.right != null) {
            str =
                str +
                "Interval=[" +
                node.right.startInterval +
                "-" +
                node.right.endInterval +
                "] and data: " +
                node.right.data;
        } else {
            str = str + "No right child";
        }

        console.log(str + "\n");

        // call recursion
        if (node.left != null) {
            this.display(node.left);
        }

        if (node.right != null) {
            this.display(node.right);
        }
    }

    query(node, qsi, qei) {
        if (node.startInterval >= qsi && node.endInterval <= qei) {
            // node is completely lying inside query
            return node.data;
        } else if (node.startInterval > qei || node.endInterval < qsi) {
            // completely ouside
            return 0;
        } else {
            // Overlapping case
            return (
                this.query(node.left, qsi, qei) +
                this.query(node.right, qsi, qei)
            );
        }
    }

    update(node, index, value) {
        if (index >= node.startInterval && index <= node.endInterval) {
            if (index == node.startInterval && index == node.endInterval) {
                node.data = value;
                return node.data;
            } else {
                const leftAns = this.update(node.left, index, value);
                const rightAns = this.update(node.right, index, value);
                node.data = leftAns + rightAns;
                return node.data;
            }
        }
        return node.data;
    }
}

let root = null;
let arr = [3, 8, 6, 7, -2, -8, 4, 9];
const tree = new SegmentTree();
root = tree.constructTree(arr, 0, arr.length - 1);
// tree.display(root);
// console.log(tree.query(root, 1, 6));
root.data = tree.update(root, 2, 10);
// tree.display(root);
