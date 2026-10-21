// https://leetcode.com/problems/sort-colors/description/

/*
    Given an array nums with n objects colored red, white, or blue, 
    sort them in-place so that objects of the same color are adjacent, 
    with the colors in the order red, white, and blue.

    We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

    You must solve this problem without using the library's sort function.    

    Input: nums = [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]

    Input: nums = [2,0,1]
    Output: [0,1,2]     
*/

function sortColors(nums: number[]): void {
    const max = Math.max(...nums);
    const countArr = new Array(max + 1).fill(0);

    for (let num of nums) {
        countArr[num]++;
    }

    let result: number[] = [];
    for (let i = 0; i < countArr.length; i++) {
        while (countArr[i] > 0) {
            result.push(i);
            countArr[i]--;
        }
    }
    
    for (let i = 0; i < result.length; i++) {
        nums[i] = result[i];
    }
}

const nums = [2,0,2,1,1,0]
sortColors(nums);   // Output: [0,0,1,1,2,2]

