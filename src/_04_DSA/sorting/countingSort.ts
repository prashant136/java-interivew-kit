
function countingSort(arr: number[]): number[] {
    const max = Math.max(...arr);           // Math.max(2, 4, 12, ...)
    const countArr = new Array(max + 1).fill(0);

    for (let num of arr) {
        countArr[num]++;
    }

    let result: number[] = [];
    for (let i = 0; i < countArr.length; i++) {
        while (countArr[i] > 0) {
            result.push(i);
            countArr[i]--;
        }
    }
    return result;
}

const arr = [2, 5, 3, 0, 2, 3, 0, 3];
console.log(countingSort(arr));

/*
    🔹 Complexity
    Time: O(n + k)
    Space: O(k)

    (k = range of numbers)
*/