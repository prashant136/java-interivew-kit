/***
 * 
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

    Example 1:
    Input: head = [1,2,2,1]
    Output: true

    Example 2:
    Input: head = [1,2]
    Output: false
    
    Constraints: The number of nodes in the list is in the range [1, 105].
     0 <= Node.val <= 9
    
    Follow up: Could you do it in O(n) time and O(1) space?
 */

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

    // brute force method
    palindrome(head) {
        let stack = [];
        for (let temp = head; temp !== null; temp = temp.next) {
            stack.push(temp.data);
        }

        for (let temp = head; temp !== null; temp = temp.next) {
            const popElement = stack.pop();
            if (temp.data !== popElement) return false;
        }
        return true;
    }

    reverse(head) {
        let prev = null;
        let present = head;
        let next = present.next;

        while (present) {
            present.next = prev;
            prev = present;
            present = next;
            if (next) {
                next = next.next;
            }
        }
        head = prev;
        return head;
    }

    // 👋 in place treversal
    isPalindrome(head) {
        // find middle
        let slow = head;
        let fast = head;
        while (fast.next !== null && fast.next.next !== null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // reverse the last half
        let newHead = this.reverse(slow.next);
        // compare both halves
        let first = head;
        let second = newHead;
        while (second !== null) {
            if (first.data !== second.data) {
                this.reverse(newHead);  // re-reverse the last half. bring back to original
                return false;
            }
            first = first.next;
            second = second.next;
        }
        this.reverse(newHead);
        return true;
    }

    print() {
        for (let temp = this.head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const ll = new LL();
const arr = [1, 2];
let head = ll.constructLLfromArray(arr);
// console.log(ll.palindrome(head));
console.log(ll.isPalindrome(head));
// ll.print();
