// https://leetcode.com/problems/path-with-minimum-effort/description/
/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 *  where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 *  and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 *  You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

    Example 1:
    Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
    Output: 2
    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
    
    Example 2:
    Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
    Output: 1
    Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
    
    Example 3:
    Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
    Output: 0
    Explanation: This route does not require any effort.
 */

// 🧠 You're given a 2D heights grid. You can move up/down/left/right.
// 👉 The effort between two cells is abs(height1 - height2).

// Your goal is to find a path from top-left to bottom-right such that the maximum effort among all steps is minimized.
// This is a classic Dijkstra problem, but instead of summing edge weights, we take the maximum of current and next edge cost.

type Cell = [number, number, number]; // [effort, x, y]

class MinHeap {
  heap: Cell[] = [];

  push(val: Cell) {
    this.heap.push(val);
    this.bubbleUp();
  }

  pop(): Cell | undefined {
    if (this.heap.length === 0) return undefined;
    const top = this.heap[0];
    const end = this.heap.pop()!;
    if (this.heap.length > 0) {
      this.heap[0] = end;
      this.bubbleDown();
    }
    return top;
  }

  peek(): Cell | undefined {
    return this.heap[0];
  }

  size(): number {
    return this.heap.length;
  }

  bubbleUp() {
    let index = this.heap.length - 1;
    const element = this.heap[index];

    while (index > 0) {
      const parentIndex = Math.floor((index - 1) / 2);
      if (this.heap[parentIndex][0] <= element[0]) break;
      this.heap[index] = this.heap[parentIndex];
      index = parentIndex;
    }
    this.heap[index] = element;
  }

  bubbleDown() {
    let index = 0;
    const length = this.heap.length;
    const element = this.heap[0];

    while (true) {
      let left = 2 * index + 1;
      let right = 2 * index + 2;
      let smallest = index;

      if (left < length && this.heap[left][0] < this.heap[smallest][0])
        smallest = left;
      if (right < length && this.heap[right][0] < this.heap[smallest][0])
        smallest = right;
      if (smallest === index) break;

      this.heap[index] = this.heap[smallest];
      index = smallest;
    }
    this.heap[index] = element;
  }
}

function minimumEffortPath(heights: number[][]): number {
  const n = heights.length;
  const m = heights[0].length;

  const dx = [-1, 0, 1, 0];
  const dy = [0, 1, 0, -1];

  const dist: number[][] = Array.from({ length: n }, () => Array(m).fill(Infinity));
  dist[0][0] = 0;

  const pq = new MinHeap();
  pq.push([0, 0, 0]); // [effort, x, y]

  while (pq.size() > 0) {
    const [dist_of_par, x, y] = pq.pop()!;

    for (let k = 0; k < 4; k++) {
      const ii = x + dx[k];
      const jj = y + dy[k];

      if (ii < 0 || jj < 0 || ii >= n || jj >= m) continue;

      const new_dist_of_nbr = Math.max(
        dist_of_par,
        Math.abs(heights[x][y] - heights[ii][jj])
      );

      if (new_dist_of_nbr < dist[ii][jj]) {
        dist[ii][jj] = new_dist_of_nbr;
        pq.push([dist[ii][jj], ii, jj]);
      }
    }
  }

  return dist[n - 1][m - 1];
}

const input = [
  [1,2,2],
  [3,8,2],
  [5,3,5]
];

console.log(minimumEffortPath(input)); // Output: 2

