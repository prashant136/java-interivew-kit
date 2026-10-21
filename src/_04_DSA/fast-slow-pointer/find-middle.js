class ListNode {
    constructor(data) {
        this.data = data;
        this.next = null;
    }
}

class LL {
    constructor() {
        this.head = null;
    }

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

    // 👋 fast and slow
    findMiddle(head) {
        let slow = head;
        let fast = head;
        while (fast !== null && fast.next !== null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    print() {
        for (let temp = this.head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const ll = new LL();
const arr = [1, 12, 6, 8, 2, 7];
let head = ll.constructLLfromArray(arr);
console.log(ll.findMiddle(head));
