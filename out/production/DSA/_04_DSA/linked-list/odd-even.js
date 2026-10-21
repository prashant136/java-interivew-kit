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

    oddEvenList(head) {
        if (!head || !head.next) {
            return head;
        }

        let odd = head;
        let even = head.next;
        const evenHead = head.next;

        while (even !== null && even.next !== null) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    print(head) {
        for (let temp = head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const ll = new LL();
const arr = [10, 23, 4, 1, 12];
let head = ll.constructLLfromArray(arr);
head = ll.oddEvenList(head);
ll.print(head);
