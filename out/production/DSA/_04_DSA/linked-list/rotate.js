class ListNode {
    constructor(data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    constructor() {
        this.head = null;
    }

    // construct linked list
    constructLLfromArray(arr) {
        this.head = new ListNode(arr[0]);
        let mover = this.head;
        for (let i = 1; i < arr.length; i++) {
            const node = new ListNode(arr[i]);
            mover.next = node;
            mover = mover.next;
        }
        return this.head;
    }

    size(head) {
        let count = 0;
        for (let current = head; current !== null; current = current.next) {
            count++;
        }
        return count;
    }

    rotate(head, k) {
        if(head === null) return head;
        const len = this.size(head);
        const newK = k % len;       // k will be multiple of like 5, 10, 15, 20, 8...
        if (newK == 0) return head;

        // find tail
        let tail = head;
        while (tail.next !== null) {
            tail = tail.next;
        }

        // attach tail node to head
        tail.next = head;

        let newHead = head;
        for (let i = 1; i < len - newK; i++) {
            newHead = newHead.next;
        }

        head = newHead;
        newHead = newHead.next;
        head.next = null;

        return newHead;
    }

    print(head) {
        for (let temp = head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const list = new LinkedList();
const arr = [3, 7, 12, -1, 34, 10];
let head = list.constructLLfromArray(arr);
head = list.rotate(head, 3);
list.print(head);
