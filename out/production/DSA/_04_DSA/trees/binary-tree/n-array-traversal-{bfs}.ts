class NArryaTreeNode<T> {
    value: T;
    children: NArryaTreeNode<T>[];

    constructor(value: T) {
        this.value = value;
        this.children = [];
    }
}

const NArrayBfsTraversal = <T>(root: NArryaTreeNode<T> | null): T[] => {
    const result: T[] = [];
    const queue: NArryaTreeNode<T>[] = [];

    if (!root) return result;
    queue.push(root);

    while (queue.length) {
        const node = queue.shift();
        if (node) {
            result.push(node.value);
            // Push all children to the queue.
            // queue.push(...node.children); // This is the same as the loop below.
            for(let child of node.children) {
                queue.push(child);
            }
        }
    }
    return result;
};

// Example usage
const root = new NArryaTreeNode(1);
root.children.push(new NArryaTreeNode(2));
root.children.push(new NArryaTreeNode(3));
root.children[0].children.push(new NArryaTreeNode(4));
root.children[0].children.push(new NArryaTreeNode(5));
root.children[1].children.push(new NArryaTreeNode(6));
root.children[1].children.push(new NArryaTreeNode(7));

console.log(NArrayBfsTraversal(root)); // [1, 2, 3, 4, 5, 6, 7]
