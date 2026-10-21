class Node {
    constructor(value) {
        this.value = value;
        this.next = null;
        this.bottom = null;
    }
}

class LinkedList {
    constructor() {
        this.head = null;
    }
    // construct linked list
    createLinkedList(arr) {
        if (arr.length === 0) return null;

        this.head = new Node(arr[0]);
        let current = this.head;

        for (let i = 1; i < arr.length; i++) {
            current.bottom = new Node(arr[i]);
            current = current.bottom;
        }

        return this.head;
    }

    linkNextNodes(mainList) {
        for (let i = 0; i < mainList.length - 1; i++) {
            mainList[i].next = mainList[i + 1];
        }
    }

    printMultiLevelList(head) {
        let mainCurrent = head;
        while (mainCurrent !== null) {
            let bottomCurrent = mainCurrent;
            while (bottomCurrent !== null) {
                process.stdout.write(bottomCurrent.value + " ");
                bottomCurrent = bottomCurrent.bottom;
            }
            console.log();
            mainCurrent = mainCurrent.next;
        }
    }

    // ----------------- flatten the above linked list -------------------
    // using extra space ---- see later
    extractElementFromList(head) {
        let arr = [];
        let mainCurrent = head;
        while (mainCurrent !== null) {
            let bottomCurrent = mainCurrent;
            while (bottomCurrent !== null) {
                arr.push(bottomCurrent.value);
                bottomCurrent = bottomCurrent.bottom;
            }
            mainCurrent = mainCurrent.next;
        }
        return arr;
    }

    sortAndConvertToLinkedList(head) {
        let arr = this.extractElementFromList(head);
        arr.sort();
        this.head = new Node(arr[0]);
        let mover = this.head;
        for (let i = 1; i < arr.length; i++) {
            const node = new Node(arr[i]);
            mover.next = node;
            mover = mover.next;
        }
        return this.head;
    }
    print(head) {
        for (let temp = head; temp !== null; temp = temp.next) {
            console.log(temp.value);
        }
    }
}

// Creating sub-linked-lists
let ll = new LinkedList();
let subList1 = ll.createLinkedList([5, 7, 8, 30]);
let subList2 = ll.createLinkedList([10, 20]);
let subList3 = ll.createLinkedList([19, 22, 50]);
let subList4 = ll.createLinkedList([28, 35, 40, 45]);

let mainList = [subList1, subList2, subList3, subList4]; // Storing sub-linked-lists in an array
ll.linkNextNodes(mainList); // Linking the nodes with the 'next' pointer

// Now, `mainList[0]` is the head of the multi-level linked list.
// ll.printMultiLevelList(mainList[0]);

ll.sortAndConvertToLinkedList(mainList[0]);
ll.print(mainList[0]);
