// select the minimum then swap with first element
function selectionSort(arr, n) {
    for (let i = 0; i < n - 1; i++) {
        let min = i;
        for (let j = i + 1; j < n; j++) {
            if (arr[j] < arr[min]) {
                min = j;
            }
        }
        //swap
        [arr[i], arr[min]] = [arr[min], arr[i]];
    }
    return arr;
}

// push the maximum at last by adjcent swap
function bubbleSort(arr, n) {
    for (let i = n - 1; i >= 0; i--) {
        for (let j = 0; j <= i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                //swap
                [arr[j], arr[j + 1]] = [arr[j + 1], arr[j]];
            }
        }
    }
    return arr;
}

// take the element and place to currect order
function insertionSort(arr, n) {
    for (let i = 0; i <= n - 1; i++) {
        let j = i;
        while (j > 0 && arr[j - 1] > arr[j]) {
            //swap
            [arr[j - 1], arr[j]] = [arr[j], arr[j - 1]];
            j--;
        }
    }
    return arr;
}

// const arr = [13, 46, 24, 52, 20, 9];
// console.log(selectionSort(arr, arr.length));
// console.log(bubbleSort(arr, arr.length));
// console.log(insertionSort(arr, arr.length));

