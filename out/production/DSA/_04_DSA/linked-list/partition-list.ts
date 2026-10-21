namespace ll1 {
    class ListNode {
        value: number;
        next: ListNode | null;
    
        constructor(value: number, next: ListNode | null = null) {
            this.value = value;
            this.next = next;
        }
    }
    
    class LinkedList {
        head: ListNode | null;
    
        constructor() {
            this.head = null;
        }
    
        buildLinkedList(arr: number[]) {
            this.head = new ListNode(arr[0]);
            let mover = this.head;
            for (let i = 1; i < arr.length; i++) {
                const node = new ListNode(arr[i]);
                mover.next = node;
                mover = mover.next;
            }
            return this.head;
        }

        print(head: ListNode | null) {
            for (let temp = head; temp !== null; temp = temp.next) {
                console.log(temp.value);
            }
        }
    
        partition(head: ListNode | null, x: number): ListNode | null {
            let small = new ListNode(-1);
            let large = new ListNode(-1);
            let ptrSm = small;
            let ptrLg = large;
        
            while(head !== null) {
                if(head.value < x) {
                    ptrSm.next = head;
                    ptrSm = ptrSm.next;
                } else {
                    ptrLg.next = head;
                    ptrLg = ptrLg.next;
                }
                head = head.next;
            }
            ptrSm.next = large.next;
            ptrLg.next = null;
        
            return small.next;
        };
    }
    
    const linkedlist = new LinkedList();
    const arr = [1,4,3,2,5,2], x = 3;
    let head = linkedlist.buildLinkedList(arr);
    let ansHead = linkedlist.partition(head, x);
    linkedlist.print(ansHead);
    
}
