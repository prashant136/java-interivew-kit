class ListNode {
    val: number;
    next: ListNode | null;

    constructor(val = 0, next: ListNode | null = null) {
        this.val = val;
        this.next = next;
    }
}

// ----------- Min Heap Implementation -----------
class MinHeap {
    heap: ListNode[] = [];

    swap(i: number, j: number) {
        [this.heap[i], this.heap[j]] = [this.heap[j], this.heap[i]];
    }

    push(node: ListNode) {
        this.heap.push(node);
        this.heapifyUp();
    }

    heapifyUp() {
        let i = this.heap.length - 1;

        while (i > 0) {
            let parent = Math.floor((i - 1) / 2);

            if (this.heap[parent].val <= this.heap[i].val) break;

            this.swap(parent, i);
            i = parent;
        }
    }

    pop(): ListNode {
        const top = this.heap[0];
        const last = this.heap.pop()!;

        if (this.heap.length > 0) {
            this.heap[0] = last;
            this.heapifyDown();
        }

        return top;
    }

    heapifyDown() {
        let i = 0;

        while (true) {
            let left = 2 * i + 1;
            let right = 2 * i + 2;
            let smallest = i;

            if (left < this.heap.length && this.heap[left].val < this.heap[smallest].val)
                smallest = left;

            if (right < this.heap.length && this.heap[right].val < this.heap[smallest].val)
                smallest = right;

            if (smallest === i) break;

            this.swap(i, smallest);
            i = smallest;
        }
    }

    size() {
        return this.heap.length;
    }
}

// ----------- Merge K Lists -----------
function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const minHeap = new MinHeap();

    // Push initial heads
    for (let list of lists) {
        if (list) minHeap.push(list);
    }

    let dummy = new ListNode(0);
    let curr = dummy;

    while (minHeap.size() > 0) {
        let node = minHeap.pop();

        curr.next = node;
        curr = curr.next;

        if (node.next) {
            minHeap.push(node.next);
        }
    }

    return dummy.next;
}

// ----------- Helper to create linked list -----------
function createList(arr: number[]): ListNode | null {
    let dummy = new ListNode(0);
    let curr = dummy;

    for (let num of arr) {
        curr.next = new ListNode(num);
        curr = curr.next;
    }

    return dummy.next;
}

// ----------- Helper to print linked list -----------
function printList(head: ListNode | null) {
    let res: number[] = [];

    while (head) {
        res.push(head.val);
        head = head.next;
    }

    console.log(res.join(" -> "));
}

// ----------- Example Run -----------

const l1 = createList([1,4,5]);
const l2 = createList([1,3,4]);
const l3 = createList([2,6]);

const result = mergeKLists([l1, l2, l3]);

printList(result);