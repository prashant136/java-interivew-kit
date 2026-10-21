// https://leetcode.com/problems/reorder-list/?envType=problem-list-v2&envId=stack

/*
    Reorder List =
    Find middle → Reverse second half → Merge two halves

    No $04DSA.stack. No extra nodes. No extra space.
*/

class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = (val === undefined ? 0 : val)
        this.next = (next === undefined ? null : next)
    }
}

function reorderList(head: ListNode | null): void {
    if (!head || !head.next) return;

    // 1. Find middle
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;

    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }

    // 2. Reverse second half
    let prev: ListNode | null = null;
    let curr: ListNode | null = slow!.next;
    slow!.next = null; // split the list

    while (curr) {
        const nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }

    // 3. Merge two halves
    let first: ListNode | null = head;
    let second: ListNode | null = prev;

    while (second) {
        const temp1 = first!.next;
        const temp2 = second.next;

        first!.next = second;
        second.next = temp1;

        first = temp1;
        second = temp2;
    }
}
