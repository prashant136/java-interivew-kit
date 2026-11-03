package _04_DSA;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

/*
        ✅ 1. What is a Heap? (Simple Intuition)
            A Heap is a special type of Binary Tree that follows two rules:


        🔷 Rule 1 — Complete Binary Tree
            A heap is always a complete binary tree.

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

        1. Max Heap - Parent is always greater than children

            50
           /  \
          30   40        parent >= children .... [ Largest element always at root ]
         /  \
        10  20


        2. Min Heap - Parent is always less than children

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

        In interviews we do NOT build tree nodes. We store heap in array.

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
        1. Insert (Heapify Up)
        2. Delete Root (Heapify Down)


        1. Insert (Heapify Up) - When we insert a new element, we add it at the end of the array (maintaining complete tree property) and then "heapify up" to restore the $04DSA.heap property.

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


        2. Delete Root (Heapify Down) - When we delete the root, we replace it with the last element in the array and then "heapify down" to restore the $04DSA.heap property.

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
    private List<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    /// Insert a new value into the heap
    // Steps:
    //    1. Insert element at the end of list.
    //    2. Restore heap property using HEAPIFY UP.

    public void insert(int val) {
        heap.add(val);
        heapifyUp();
    }

    /// HEAPIFY UP (Bubble Up)
    //    Used after insertion.
    //    Idea:
    //    - Compare inserted element with its parent.
    //    - If child > parent -> swap.
    //    - Continue until heap property is restored.
    private void swap(List<Integer> heap, int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    public void heapifyUp() {
        int i = heap.size() - 1;    // Start from last inserted element

        while (i > 0) {
            int parent = (i - 1) / 2;    // find parent index
            // if parent is already larger, heap property is satisfied
            if (heap.get(parent) >= heap.get(i)) break;
            Collections.swap(heap, parent, i);   // otherwise swap child with parent
            i = parent;    // move pointer to parent index
        }
    }

    /// Remove the root element (maximum element).
    // Steps:
    // - Store root value to return later.
    // - Replace root with last element.
    // - Remove last element.
    // - Restore heap property using HEAPIFY DOWN.

    public Integer remove() {
        if(heap.isEmpty()) return null;

        // If only one element
        if (heap.isEmpty()) {
            return heap.remove(0);
        }

        int root = heap.get(0);  // Store max element to return later

        // Move the last element to the root and remove the last element
        heap.set(0, heap.remove(heap.size() - 1));

        heapifyDown();  // Restore heap property

        return root;
    }

    /// HEAPIFY DOWN (Bubble Down)
    // Used after removing the root.
    // Idea:
    // - Compare parent with its children.
    // - Swap with the LARGEST child.
    // - Continue until heap property is restored.

    private void heapifyDown() {
        int i = 0; // Start from root
        int n = heap.size();

        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            // Check if left child exists and is larger than current largest
            if (left < n && heap.get(left) > heap.get(largest)) {
                largest = left;
            }

            // Check if right child exists and is larger than current largest
            if (right < n && heap.get(right) > heap.get(largest)) {
                largest = right;
            }

            // If parent is already the largest, heap property is satisfied
            if (largest == i) {
                break;
            }

            // Swap parent with the largest child
            Collections.swap(heap, i, largest);

            // Move pointer downward
            i = largest;
        }
    }

    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(10);
        maxHeap.insert(30);
        maxHeap.insert(20);
        maxHeap.insert(5);

        System.out.print("Heap after insertions: ");
        maxHeap.printHeap(); // Output: [30, 10, 20, 5]

        maxHeap.remove();
        System.out.print("Heap after remove: ");
        maxHeap.printHeap(); // Output: [20, 10, 5]
    }
}

// 👀 👉 clean and compact reusable heap template for interviews. don't need to wrote long $04DSA.heap class.
// class MaxHeap {
//     heap: number[] = [];

//     insert(val: number) {
//         this.heap.push(val);

//         let i = this.heap.length - 1;

//         // heapify up
//         while (i > 0) {
//             let parent = Math.floor((i - 1) / 2);

//             if (this.$04DSA.heap[parent] >= this.heap[i]) break;

//             [this.heap[parent], this.heap[i]] = [this.heap[i], this.heap[parent]];

//             i = parent;
//         }
//     }

//     remove() {
//         if (!this.heap.length) return null;

//         let root = this.heap[0];
//         this.heap[0] = this.heap.pop()!;

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

/// ----------- built in priority queue method ------------
// offer(element) - for insertion - (void)
// poll() - for remove top element - (T)
// peek() - peek element (T)
// size() - queue size (int)
// isEmpty() - check queue is empty or not (boolean)

public class _7_PriorityQueue {

    public int KthLargest(int k, int[] nums) {
        return 0;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // min heap
        for (int num : nums) {
            pq.offer(num);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }



    void main() {
        System.out.println("KthLargest: " + KthLargest(3, new int[]{4, 6, 10, 8, 7, 8, 2}));
        System.out.println("findKthLargest: " + findKthLargest(new int[]{4, 6, 10, 8, 7, 8, 2}, 3));


    }
}

