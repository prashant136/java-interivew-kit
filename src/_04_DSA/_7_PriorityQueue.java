package _04_DSA;

import java.util.PriorityQueue;

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
    }
}

