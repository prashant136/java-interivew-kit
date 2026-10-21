function quickSort(arr: number[], low: number, high: number) {
    if (low >= high) return;
    let partionIndex = partition(arr, low, high);
    console.log({ partionIndex });
    quickSort(arr, low, partionIndex - 1);
    quickSort(arr, partionIndex + 1, high);
}

function partition(arr: number[], low: number, high: number) {
    let pivot = arr[low];       // we take pivot as first element
    let i = low;                
    let j = high;
    
    while (i < j) {
        while (arr[i] <= pivot && i <= high - 1) i++;       // find the element greater than pivot
        while (arr[j] > pivot && j >= low - 1) j--;         // find the element smaller than pivot
        if (i < j) {        // now we find both elements -> swap (if they haven't crossed the boundary)
            [arr[i], arr[j]] = [arr[j], arr[i]];
        }
    }

    [arr[low], arr[j]] = [arr[j], arr[low]];        // swap pivot and place it to its actual position
    return j;   // return the partion index
}

const arr = [4, 6, 2, 5, 7, 9, 1, 3];
quickSort(arr, 0, arr.length - 1);
// console.log(arr);