class DoublyLinkedListNode {
    key: number;
    value: number;
    prev: DoublyLinkedListNode | null = null;
    next: DoublyLinkedListNode | null = null;
    constructor(key: number, value: number) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private capacity: number;
    private map: Map<number, DoublyLinkedListNode>
    private head: DoublyLinkedListNode
    private tail: DoublyLinkedListNode

    constructor(capacity: number) {
        this.capacity = capacity;
        this.map = new Map();

        // Dummy head and tail
        this.head = new DoublyLinkedListNode(0, 0)
        this.tail = new DoublyLinkedListNode(0, 0)

        this.head.next = this.tail
        this.tail.prev = this.head;
    }

    get(key: number): number {
        const node = this.map.get(key);
        if(!node) return -1;
        this.remove(node);
        this.addToFront(node);
        return node!.value;
    }

    put(key: number, value: number) {
        // if key found in hashmap
        if(this.map.get(key)) {
            const node = this.map.get(key);
            node!.value = value;
            this.remove(node);
            this.addToFront(node);
        } else {
            // if capacity is full -> then the last node then add new node in front.
            if(this.capacity === this.map.size) {
                // Remove least recently used (LRU)
                const node = this.tail.prev;  // find last node with the help of tail
                this.remove(node);  // delete the node
                this.map.delete(node.key);   // delete from map
            }

            const newNode = new DoublyLinkedListNode(key, value);   // create new node
            this.addToFront(newNode);   // add to the front
            this.map.set(key, newNode);    // set to map
        }
    }

    // ---------- Helper functions ------------
    remove(node: DoublyLinkedListNode): void {
        node.prev!.next = node.next;
        node.next!.prev = node.prev;
    }

    addToFront(node: DoublyLinkedListNode): void {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next!.prev = node;
        this.head.next = node;
    }
}

const cache = new LRUCache(2)

cache.put(1, 1)
cache.put(2, 2)
cache.get(1)    // returns 1 (1 becomes most recent)
cache.put(3, 3) // evicts key 2
cache.get(2)    // returns -1

// Why singly LL doesn’t work ?
//  Problem 1: Problem 1: No prev pointer -> You cannot remove node unless you know prev. 
//  Problem 2: Tail removal is expensive -> You must traverse from head to find second-last -> O(n)

/*
 * head <-> most recent <-> ... <-> least recent <-> tail
 
 - get(key)
    If key not in map → return -1
    Else:
        Move node to front
        Return value
        
        
    put(key, value)
        If key exists:
            Update value
            Move node to front
        Else:
            If cache full:
                Remove tail.prev (LRU)
                Delete from map

            Insert new node at front
            Add to map
 */
