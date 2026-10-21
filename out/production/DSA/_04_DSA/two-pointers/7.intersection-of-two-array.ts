
// brute force
function intersect(nums1: number[], nums2: number[]): number[] {
    let result: number[] = [];
    let visited = new Array(nums2.length).fill(false);

    for (let i = 0; i < nums1.length; i++) {
        for (let j = 0; j < nums2.length; j++) {
            if (!visited[j] && nums1[i] === nums2[j]) {
                result.push(nums1[i]);
                visited[j] = true;
                break;
            }
        }
    }

    return result;
}


// $04DSA.sorting + two pointers
// Time - O(n log n + m log m)
function intersect(nums1: number[], nums2: number[]): number[] {
    nums1.sort((a, b) => a - b);
    nums2.sort((a, b) => a - b);

    let i = 0, j = 0;
    let result: number[] = [];

    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] === nums2[j]) {
            result.push(nums1[i]);
            i++;
            j++;
        } else if (nums1[i] < nums2[j]) {
            i++;
        } else {
            j++;
        }
    }

    return result;
}


// hashmap
// time - O(n + m)
function intersect(nums1: number[], nums2: number[]): number[] {
    let map = new Map<number, number>();

    // Step 1: count freq of nums1
    for (let num of nums1) {
        map.set(num, (map.get(num) || 0) + 1);
    }

    let result: number[] = [];

    // Step 2: check nums2
    for (let num of nums2) {
        if ((map.get(num) || 0) > 0) {
            result.push(num);
            map.set(num, map.get(num)! - 1);
        }
    }

    return result;
}