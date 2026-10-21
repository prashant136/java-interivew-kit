class Node {
    constructor(data) {
        this.data = data;
        this.next = null;
        // this.right = null;
    }
}

class LL {
    constructor() {
        this.head = null;
    }

    constructLLfromArray(arr) {
        this.head = new Node(arr[0]);
        let mover = this.head;
        for (let i = 1; i < arr.length; i++) {
            const node = new Node(arr[i]);
            mover.next = node;
            mover = mover.next;
        }
    }

    print() {
        for (let temp = this.head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }

    // time and space - O(n)
    reverse() {
        let stack = [];
        // push all item in $04DSA.stack in order to reverse it.
        for (let temp = this.head; temp !== null; temp = temp.next) {
            stack.push(temp.data);
        }
        // pop element form $04DSA.stack
        let mover = this.head;
        while (stack.length && mover !== null) {
            const popElement = stack.pop();
            mover.data = popElement;
            mover = mover.next;
        }
    }

    reverse_in_place_trversal() {
        let prev = null;
        let present = this.head;
        let next = present.next;

        while (present) {
            present.next = prev;
            prev = present;
            present = next;
            if (next) {
                next = next.next;
            }
        }
        this.head = prev;
    }

    reverseList(head: ListNode | null): ListNode | null {
    let prev: ListNode | null = null;
    let curr: ListNode | null = head;

    while (curr) {
        const nextTemp = curr.next; // save next
        curr.next = prev;           // reverse link
        prev = curr;                // move prev
        curr = nextTemp;            // move curr
    }

    return prev;
}

}

const ll = new LL();
const arr = [10, 23, 4, 12];
ll.constructLLfromArray(arr);
// ll.print();
// ll.reverse();
ll.reverse_in_place_trversal();
ll.print();
