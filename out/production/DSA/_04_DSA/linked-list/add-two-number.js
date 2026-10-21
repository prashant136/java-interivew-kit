// https://leetcode.com/problems/add-two-numbers/
// https://www.youtube.com/watch?v=XmRrGzR6udg&list=PLgUwDviBIf0rAuz8tVcM0AymmhTRsfaLU&index=7

/**
 * 
 * Add Two Numbers
    You are given two non-empty linked lists representing two non-negative integers. 
    The digits are stored in reverse order, and each of their nodes contains a single digit. 
    Add the two numbers and return the sum as a linked list.
    
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example 1:
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.

    Example 2:
    Input: l1 = [0], l2 = [0]
    Output: [0]

    Example 3:
    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    Output: [8,9,9,9,0,0,0,1]
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
    }

    addTwoNumbers(l1, l2) {
        let dummyHead = new ListNode(0);
        let curr = dummyHead;
        let carry = 0;

        while (l1 !== null || l2 !== null) {
            let x = l1 !== null ? l1.data : 0;
            let y = l2 !== null ? l2.data : 0;
            let sum = carry + x + y;
            carry = Math.floor(sum / 10);
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 !== null) l1 = l1.next;
            if (l2 !== null) l2 = l2.next;
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    print(head) {
        for (let temp = head; temp !== null; temp = temp.next) {
            console.log(temp.data);
        }
    }
}

const ll = new LL();
const arr1 = [1, 8, 4];
const arr2 = [1, 4, 5, 2];
let l1 = ll.constructLLfromArray(arr1);
let l2 = ll.constructLLfromArray(arr2);
let head = ll.addTwoNumbers(l1, l2);
ll.print(head);
