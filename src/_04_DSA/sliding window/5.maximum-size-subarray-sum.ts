
const maxSubArrayLength = (nums: number[], target: number) => {
    let i = 0;
    let sum = 0;
    let ans = 0;

    for (let j = 0; j < nums.length; j++) {
        sum += nums[j];

        while (sum > target) {
            sum -= nums[i];
            i++;
        }

        if (sum === target) {
            ans = Math.max(ans, j - i + 1);
        }
    }

    return ans;
};

