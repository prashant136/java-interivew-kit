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

    // make a cycle (loop) in lisnked list
    makeCycle(head, k) {
        let temp = head;
        for (let i = 0; i < k; i++) {
            temp = temp.next;
        }

        let intersectionPoint = temp;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = intersectionPoint;
        return head;
    }

    // ⛈️ 👉 detect cycle -- space and time both - O(N)
    hasCycle(head) {
        const seenNodes = new Set();

        let currentNode = head;
        while (currentNode !== null) {
            // If current node is already in the set, cycle detected
            if (seenNodes.has(currentNode)) {
                return true;
            }
            seenNodes.add(currentNode);
            currentNode = currentNode.next;
        }
        return false; // No cycle found
    }

    // 👋 fast and slow pointer
    detectCycle(head) {
        let slow = head;
        let fast = head;
        while (fast !== null && fast.next !== null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast === slow) return true;
        }
        return false;
    }

    findlength(fast, slow) {
        let count = 1;
        fast = fast.next;
        while (slow !== fast) {
            count++;
            fast = fast.next;
        }
        return count;
    }
    // length of loop
    lenghtofLoop(head) {
        let slow = head;
        let fast = head;
        while (fast !== null && fast.next !== null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast === slow) return this.findlength(fast, slow);
        }
        return 0;
    }

    startingPointofloop(head) {
        let slow = head;
        let fast = head;
        while (fast !== null && fast.next !== null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast === slow) {
                // keep slow at head of pointer,
                slow = head;
                while (slow !== fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    print(head) {
        for (let temp = head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const list = new LinkedList();
const arr = [1, 12, 6, 8, 12, -7, 1, 34];
let head = list.constructLLfromArray(arr);
head = list.makeCycle(head, 3);
// console.log(list.hasCycle(head));
// console.log(list.detectCycle(head));
// console.log(list.lenghtofLoop(head));
// console.log(list.startingPointofloop(head));
