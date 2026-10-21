import { ListNode } from "./linkedlist-utils.ts";

export class LinkedList {
    head: ListNode | null;

    constructor() {
        this.head = null;
    }

    addFirst(val: number) {
        const newNode = new ListNode(val);
        newNode.next = this.head;
        this.head = newNode;
    }

    addLast(val: number) {
        const newNode = new ListNode(val);
        // if there is no node initially
        if (!this.head) {
            this.head = newNode;
            return;
        }

        let current = this.head;
        while (current.next) {
            current = current.next;
        }
        current.next = newNode;
    }

    size() {
        let count = 0;
        let current = this.head;
        while (current) {
            count++;
            current = current.next;
        }
        return count;
    }

    addAt(val: number, index: number) {
        const newNode = new ListNode(val);
        if (index < 0 || index > this.size()) {
            console.log("Invalid Error");
            return;
        }

        if (index === 0) {
            this.addFirst(val);
            return;
        }
        let current = this.head;
        for (let i = 0; i < index - 1; i++) {
            if(current) current = current.next;
        }
        if(current) {
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    deleteFirst() {
        if (!this.head) {
            return;
        }
        this.head = this.head.next;
    }

    deleteLast() {
        if (!this.head) {
            return;
        }

        let current = this.head;
        while (current) {
            current = current.next;
        }
        if(current && current.next) current.next = null;
    }

    deleteAt(index: number) {
        if (index < 0 || index > this.size()) {
            console.log("Invalid Error");
            return;
        }

        if (index === 0) {
            this.deleteFirst();
            return;
        }
        let current = this.head;
        for (let i = 0; i < index - 1; i++) {
            current = current.next;
        }
        if (current && current.next) {
            current.next = current.next.next;
        }
    }

    print() {
        console.log('print run...');
        
        let current = this.head;
        while (current) {
            console.log(current.value);
            current = current.next;
        }
    }

}

// const linkedlist = new LinkedList();
// linkedlist.addFirst(10);
// linkedlist.addFirst(20);
// linkedlist.addLast(30);
// linkedlist.addLast(100);
// linkedlist.addAt(40, 3);
// linkedlist.deleteFirst();
// linkedlist.deleteLast();
// linkedlist.deleteAt(1);
// linkedlist.print();