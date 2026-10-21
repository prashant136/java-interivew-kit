/*
    Given an array of n integers nums and an integer target.
    Find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

    Input: nums = [-2,0,1,3], target = 2
    Output: 2
    Explanation: Because there are two triplets which sums are less than 2 ::  [-2,0,1], [-2,0,3]

    Input: nums = [0], target = 0
    Output: 0
    Explanation: The array have only one element, so no valid triplets exist.
*/

function threeSumSmaller(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let count = 0;

    for (let i = 0; i < nums.length - 2; i++) {
        let left = i + 1;
        let right = nums.length - 1;

        while (left < right) {
            const sum = nums[i] + nums[left] + nums[right];

            if (sum < target) {
                // All pairs from left to right are valid
                count += (right - left);
                left++;
            } else {
                right--;
            }
        }
    }

    return count;
};

/*
    🧠 Why This Works

        if (sum < target):

        Meaning: nums[i] + nums[left] + nums[right] < target

        Since the array is sorted:

        nums[left] <= nums[left+1] <= ... <= nums[right]

        So if the largest possible third element (right) works,

        then ALL smaller ones will also work.

        
        🔥 Key Insight

        If this works: (i, left, right)

        Then these also work:

        (i, left, right-1)
        (i, left, right-2)
        ...
        (i, left, left+1)

        How many elements are between left and right?  right - left

        So we count them in ONE STEP.
*/