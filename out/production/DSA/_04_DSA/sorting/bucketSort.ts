
function bucketSort(arr: number[]): number[] {
    const buckets: number[][] = Array.from({ length: 10 }, () => []);

    for (let num of arr) {
        const index = Math.floor(num * 10);
        buckets[index].push(num);
    }

    return buckets.flatMap(bucket => bucket.sort((a, b) => a - b));
}