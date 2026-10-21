// https://leetcode.com/problems/course-schedule-ii/description/

function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const adj: Map<number, number[]> = new Map();
    const inDegree: number[] = new Array(numCourses).fill(0);
    const queue: number[] = [];
    const ans: number[] = [];

    for (const [u, v] of prerequisites) {
        if(!adj.has(v)) {
            adj.set(v, []);
        }
        const val = adj.get(v)!;
        val.push(u);
        inDegree[u] += 1
    }

    for(let i = 0; i<numCourses; i++) {
        if(!inDegree[i]) {
            queue.push(i);
        } 
    }

    while(queue.length > 0) {
        const front = queue.shift()!;
        ans.push(front);

        for(const ele of adj.get(front) || []) {
            inDegree[ele] -= 1
            if(inDegree[ele] === 0) {
                queue.push(ele);
            }
        }
    }

    return ans;

};

const numCourses = 4;
const prerequisites = [[1,0],[2,0],[3,1],[3,2]];
findOrder(numCourses, prerequisites);