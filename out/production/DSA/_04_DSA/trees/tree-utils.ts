export class TreeNode<T> {
    value: T;
    left: TreeNode<T> | null;
    right: TreeNode<T> | null;

    constructor(value: T, left: TreeNode<T> | null = null, right: TreeNode<T> | null = null) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
