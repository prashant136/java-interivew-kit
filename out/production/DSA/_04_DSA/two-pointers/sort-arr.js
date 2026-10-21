// Segregate 0s and 1s in an array (all 0s in left all 1s in right aligned)
// Input array   =  [0, 1, 0, 1, 0, 0, 1, 1, 1, 0]
// Output array =  [0, 0, 0, 0, 0, 1, 1, 1, 1, 1]

function segregateArr(nums) {
    let left = 0;
    let right = nums.length - 1;

    while (left < right) {
        // swap condition
        if (nums[left] === 1 && nums[right] === 0) {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            left++;
            right--;
        } else if (nums[left] === 0) {
            left++;
        } else if (nums[right] === 1) {
            right--;
        }
    }
    return nums;
}

let arr = [0, 1, 0, 1, 0, 0, 1, 1, 1, 0];
// console.log(segregateArr(arr));

/**
 *
 * Given an array of integers 'a', move all the even integers at the beginning of the array followed by all the odd integers.
 * The relative order of odd or even integers does not matter. Return any array that satisfies the condition.
 *
 * input = [1,2,3,4,5]
 * output = [4,2,3,1,5]
 */

const isEven = (num) => {
    return num % 2 === 0;
};

const isOdd = (num) => {
    return num % 2 === 1;
};

const moveEvenNumber = (nums) => {
    let l = 0;
    let r = nums.length - 1;

    while (l < r) {
        if (isOdd(nums[l]) && isEven(nums[r])) {
            [nums[l], nums[r]] = [nums[r], nums[l]];
            r--;
            l++;
            console.log("isOdd(nums[l]) && isEven(nums[r])");
        }

        if (isOdd(nums[r])) {
            r--;
            console.log("isOdd(nums[r]");
        }

        if (isEven(nums[l])) {
            l++;
            console.log("isEven(nums[r]");
        }
    }
    console.log(nums);
};

let nums = [1, 2, 3, 4, 5];
console.log(moveEvenNumber(nums));
