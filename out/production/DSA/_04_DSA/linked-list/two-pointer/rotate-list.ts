/*
    Given the head of a linked list, rotate the list to the right by k places.

    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]

    Input: head = [0,1,2], k = 4
    Output: [2,0,1]
*/

class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = (val===undefined ? 0 : val)
        this.next = (next===undefined ? null : next)
    }
}

function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if(!head || k=== 0) return head;
    let slow:ListNode | null = head;
    let fast:ListNode | null = head;

    let current: ListNode | null = head;
    let len = 0
    while (current) {
        len++;
        current = current.next;
    }

    let rotateLen = k % len;
    if(rotateLen === 0) return head;

    while (rotateLen > 0) {
        fast = fast.next;
        rotateLen--;
    }

    while (fast && fast.next) {
        slow = slow.next;
        fast = fast.next;
    }

    fast.next = head;
    head = slow.next;
    slow.next = null;

    return head;
};

const list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
console.log(rotateRight(list, 2));
