/*
        ✅ 1. What is a Heap? (Simple Intuition)

        A Heap is a special type of Binary Tree that follows two rules:

        
        🔷 Rule 1 — Complete Binary Tree

        A $04DSA.heap is always a complete binary tree.

        Meaning:
            All levels are filled except possibly the last level is filled from left to right

            10
           /   \
          5     8         -> This is valid.
         / \   /
        2   3 6

            10
           /   \
          5     8
           \
            3       -> This is NOT valid because the last level is not filled from left to right.


        
        🔷 Rule 2 — Heap Property

        There are two types of $04DSA.heap.

        1️⃣ Max Heap - Parent is always greater than children

            50
           /  \
          30   40        parent >= children .... [ Largest element always at root ]
         /  \            
        10  20


        2️⃣ Min Heap - Parent is always less than children

            5
           / \
         10   15
        /  \
       20  30       parent <= children .... [ Smallest element always at root ]

       
       
       🔷 Why Heap is Used

        Heap is used when we need fast access to smallest or largest element.

        Operation	                Time
        Insert	                    O(log n)
        Delete root	                O(log n)
        Peek (top element)	        O(1)

        This is why Priority Queue uses Heap.


        
        🔷 Heap Representation (Important for Coding)

        In interviews we do NOT build tree nodes. We store $04DSA.heap in array.
        
        example -
            10
           /  \
          5    8
         / \
        2   3              Array representation: [10,5,8,2,3]

        Index Relationships (VERY IMPORTANT)
        For any node at index i:
            Left child index = 2*i + 1
            Right child index = 2*i + 2
            Parent index = Math.floor((i-1)/2)

        Example:
            index:  0 1 2 3 4
            value: [10,5,8,2,3]

                    10(0)
                    /   \
                 5(1)   8(2)
                  / \
               2(3)  3(4)

        
        
        🔷 Core Heap Operations

        There are two fundamental operations.
        1️⃣ Insert (Heapify Up)
        2️⃣ Delete Root (Heapify Down)

        
        1️⃣ Insert (Heapify Up) - When we insert a new element, we add it at the end of the array (maintaining complete tree property) and then "heapify up" to restore the $04DSA.heap property.

                10                                                      
               /  \
              5    8 
             / \
            2   3

            insert 12 [insert at last position]

                10
               /  \
              5    8 
             / \   /
            2   3 12

            heapify up: compare with parent (8), since 12 > 8, swap
            
                10
               /  \
              5    12
             / \
            2   3

            heapify up: compare with parent (10), since 12 > 10, swap

                12
               /  \
              5    10
             / \
            2   3

            This process is called Heapify Up.

            Time Complexity:  O(log n) - Because height of tree = log n

        
        2️⃣ Delete Root (Heapify Down) - When we delete the root, we replace it with the last element in the array and then "heapify down" to restore the $04DSA.heap property.

                50
               /  \
              30   40
             /  \
            10  20

            replace root with last element

                20
               /  \
              30   40
             /
            10

            compare with children :: Largest child = 40
            swap with largest child
            
                40
               /  \
              30   20
             /
            10

            This process is called Heapify Down.
            
            Time Complexity:   O(log n) - Because height of tree = log n

            
            Top element of $04DSA.heap:
                Min $04DSA.heap → smallest
                Max $04DSA.heap → largest

            Height of Heap:  log(n)
*/

/*
    Max Heap Implementation
        Properties of Max Heap:
        1. It is a COMPLETE BINARY TREE.
        2. Parent node is always GREATER than its children.

        Heap is stored in an ARRAY instead of an actual tree.

        Index relationships:
        Parent index = Math.floor((i - 1) / 2)
        Left child index = 2*i + 1
        Right child index = 2*i + 2
*/

class MaxHeap {
    heap: number[] = [];

    /*
    Insert a new value into the $04DSA.heap.

    Steps:
    1. Insert element at the end of array.
    2. Restore $04DSA.heap property using HEAPIFY UP.
    */
    insert(val: number) {
        this.heap.push(val);        // add element at last position
        this.heapifyUp();           // fix $04DSA.heap property
    }

    /*
    HEAPIFY UP (Bubble Up)

    Used after insertion.

    Idea:
    - Compare inserted element with its parent.
    - If child > parent → swap.
    - Continue until $04DSA.heap property is restored.
    */
    heapifyUp() {
        // Start from last inserted element
        let i = this.heap.length - 1;

        while (i > 0) {
            // find parent index
            let parent = Math.floor((i - 1) / 2);

            // if parent is already larger, $04DSA.heap property satisfied
            if (this.heap[parent] >= this.heap[i]) break;

            // otherwise swap child with parent
            [this.heap[parent], this.heap[i]] = [this.heap[i], this.heap[parent]];

            // move pointer to parent index
            i = parent;
        }
    }

    /*
    Remove the root element (maximum element).

    Steps:
    1. Store root value to return later.
    2. Replace root with last element.
    3. Remove last element.
    4. Restore $04DSA.heap property using HEAPIFY DOWN.
    */
    remove() {
        // If only one element
        if (this.heap.length === 1) return this.heap.pop();

        let root = this.heap[0];            // max element

        // Move last element to root
        this.heap[0] = this.heap.pop()!;

        // Restore $04DSA.heap property
        this.heapifyDown();

        return root;
    }

    /*
    HEAPIFY DOWN (Bubble Down)

    Used after removing the root.

    Idea:
    - Compare parent with its children.
    - Swap with the LARGEST child.
    - Continue until $04DSA.heap property is restored.
    */
    heapifyDown() {
        let i = 0;                         // start from root
        let n = this.heap.length;

        while (true) {

            // calculate child indexes
            let left = 2 * i + 1;
            let right = 2 * i + 2;

            // assume parent is largest initially
            let largest = i;

            // check if left child exists and is larger
            if (left < n && this.heap[left] > this.heap[largest])
                largest = left;

            // check if right child exists and is larger
            if (right < n && this.heap[right] > this.heap[largest])
                largest = right;

            // if parent is already largest → $04DSA.heap property satisfied
            if (largest === i) break;

            // swap parent with largest child
            [this.heap[i], this.heap[largest]] = [this.heap[largest], this.heap[i]];

            // move pointer downward
            i = largest;
        }
    }
}

const heap = new MaxHeap();

heap.insert(10);
heap.insert(30);
heap.insert(20);
heap.insert(5);
console.log(heap.heap);   // [30,10,20,5]
heap.remove();
console.log(heap.heap);   // [20,10,5]



// 👀 👉 clean and compact reusable $04DSA.heap template for interviews. don't need to wrote long $04DSA.heap class.
// class MaxHeap {
//     $04DSA.heap: number[] = [];

//     insert(val: number) {
//         this.$04DSA.heap.push(val);

//         let i = this.$04DSA.heap.length - 1;

//         // heapify up
//         while (i > 0) {
//             let parent = Math.floor((i - 1) / 2);

//             if (this.$04DSA.heap[parent] >= this.$04DSA.heap[i]) break;

//             [this.$04DSA.heap[parent], this.$04DSA.heap[i]] = [this.$04DSA.heap[i], this.$04DSA.heap[parent]];

//             i = parent;
//         }
//     }

//     remove() {
//         if (!this.$04DSA.heap.length) return null;

//         let root = this.$04DSA.heap[0];
//         this.$04DSA.heap[0] = this.$04DSA.heap.pop()!;

//         let i = 0;

//         // heapify down
//         while (true) {
//             let left = 2 * i + 1;
//             let right = 2 * i + 2;
//             let largest = i;

//             if (left < this.$04DSA.heap.length && this.$04DSA.heap[left] > this.$04DSA.heap[largest])
//                 largest = left;

//             if (right < this.$04DSA.heap.length && this.$04DSA.heap[right] > this.$04DSA.heap[largest])
//                 largest = right;

//             if (largest === i) break;

//             [this.$04DSA.heap[i], this.$04DSA.heap[largest]] = [this.$04DSA.heap[largest], this.$04DSA.heap[i]];

//             i = largest;
//         }

//         return root;
//     }
// }