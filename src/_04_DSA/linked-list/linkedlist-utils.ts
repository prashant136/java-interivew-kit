export class ListNode {
    value: number;
    next: ListNode | null;

    constructor(value: number, next: ListNode | null = null) {
        this.value = value;
        this.next = next;
    }
}

export class DoublyLinkedListNode {
    value: number;
    next: DoublyLinkedListNode | null;
    prev: DoublyLinkedListNode | null;

    constructor(value: number, next: DoublyLinkedListNode | null = null, prev: DoublyLinkedListNode | null = null) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

