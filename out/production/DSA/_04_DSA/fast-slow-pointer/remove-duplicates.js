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

    removeDuplicate(head) {
        if (head === null) return head;

        let current = head;
        while (current !== null && current.next !== null) {
            if (current.value === current.next.value) {
                current.next = current.next.next; // Skip the duplicate node
            } else {
                current = current.next; // Move to the next distinct element
            }
        }
        return head;
    }

    print(head) {
        for (let temp = head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const list = new LinkedList();
const arr = [3, 3, 3, 7, 12, -1, -1, 34];
let head = list.constructLLfromArray(arr);
head = list.removeDuplicate(head);
list.print(head);
