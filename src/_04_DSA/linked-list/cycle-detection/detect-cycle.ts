import { ListNode } from "../linkedlist-utils.ts";
import { LinkedList } from "../linked-list.ts";

function detectCycle<T>(head: ListNode<T> | null): boolean {
    let slow = head;
    let fast = head;

    while (slow && fast && fast.next) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            // cycle found
            return true;
        }
    }
    return false;
}

const list = new LinkedList<number>();
list.addLast(3);
list.addLast(5);
list.addLast(10);
list.addLast(8);
list.addLast(12);
list.addLast(-2);
list.addLast(7);

// make cycle - 
if (list.head &&
    list.head.next &&
    list.head.next.next &&
    list.head.next.next.next &&
    list.head.next.next.next.next &&
    list.head.next.next.next.next.next &&
    list.head.next.next.next.next.next) {
    list.head.next.next.next.next.next = list.head;
}

console.log(detectCycle(list.head));