class ListNode {
    val: number;
    next: ListNode | null;
    
    constructor(val: number, next: ListNode | null) {
        this.val = val;
        this.next = next;
    }
}

function mergeSort(head: ListNode) {
    if(head === null || head.next === null) return head;

    // devide into two halves
    let prev: ListNode | null = null;
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;

    while(fast && fast.next) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }

    // cut into two halves;
    prev.next = null;

    const left = mergeSort(head);
    const right = mergeSort(slow);

    merge(left, right);
}

function merge(l1: ListNode | null, l2: ListNode | null) {
    const dummyNode = new ListNode(-1, null);
    let curr = dummyNode;

    while(l1 && l2) {
        if(l1.val <= l2.val) {
            curr.next = new ListNode(l1.val, null);
            l1 = l1.next;
            curr = curr.next;
        } else {
            curr.next = new ListNode(l2.val, null);
            l2 = l2.next;
            curr = curr.next;
        }
    }

    if(l1) {
        curr.next = l1;
    }

    if(l2) {
        curr.next = l2;
    }

    return dummyNode.next;
}

const head = new ListNode(10, new ListNode(4, new ListNode(12, new ListNode(1, null))));
mergeSort(head);
