class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = val ?? 0
        this.next = next ?? null
    }
}

function mergeTwoLists(l1: ListNode | null, l2: ListNode | null): ListNode | null {
    const dummy = new ListNode(-1);
    let curr = dummy;

    while (l1 && l2) {
        if (l1.val <= l2.val) {
            curr.next = l1;
            l1 = l1.next;
        } else {
            curr.next = l2;
            l2 = l2.next;
        }
        curr = curr.next;
    }

    // Attach remaining nodes
    curr.next = l1 || l2;

    return dummy.next;
}


// const arr1 = [2, 4, 8, 10];
// const arr2 = [1, 3, 3, 6, 11, 14];
let l1 = new ListNode(2, new ListNode(4, new ListNode(8, new ListNode(10, null))))
let l2 = new ListNode(1, new ListNode(3, new ListNode(3, new ListNode(6, new ListNode(11, new ListNode(14, null))))));
let head = mergeTwoLists(l1, l2);
