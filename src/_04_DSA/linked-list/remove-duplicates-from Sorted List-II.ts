namespace ll2 {
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
    
        deleteDuplicates(head: ListNode | null): ListNode | null {
            const headRef = new ListNode(0, head);
            let temp = headRef;
            let move = head;
        
            while (move !== null) {
                if (move.next && move.value === move.next.value) {
                    const duplicateVal = move.value;
                    // Skip all nodes with this value
                    while (move && move.value === duplicateVal) {
                        move = move.next;
                    }

                    temp.next = move;
                } else {
                    temp.next = move;
                    temp = temp.next;
                    move = move.next;
                }
            }
        
            return headRef.next;
        }
        
    }
    
    const linkedlist = new LinkedList();
    // const arr = [1,2,3,3,4,4,5]
    const arr = [1,1,1,3,4]
    let head = linkedlist.buildLinkedList(arr);
    let ansHead = linkedlist.deleteDuplicates(head);
    linkedlist.print(ansHead);
    
}
