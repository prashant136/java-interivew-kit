/***
 * 
 * You are visiting a farm that has a single row of fruit $04DSA.trees arranged from left to right. The $04DSA.trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

    You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

    You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
    Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
    Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
    Given the integer array fruits, return the maximum number of fruits you can pick. 

    Example 1:

    Input: fruits = [1,2,1]
    Output: 3
    Explanation: We can pick from all 3 $04DSA.trees.
    Example 2:

    Input: fruits = [0,1,2,2]
    Output: 3
    Explanation: We can pick from $04DSA.trees [1,2,2].
    If we had started at the first tree, we would only pick from $04DSA.trees [0,1].
    Example 3:

    Input: fruits = [1,2,3,2,2]
    Output: 4
    Explanation: We can pick from $04DSA.trees [2,3,2,2].
    If we had started at the first tree, we would only pick from $04DSA.trees [1,2].
 */

//? 👉 Actual problem is => find the length of the longest contiguous subarray with at most 2 distinct integers
// ------ $04DSA.sliding window -------
function totalFruit(fruits) {
    let map = new Map();

    let ans = 0;
    let l = 0;

    for (let r = 0; r < fruits.length; r++) {
        // Update the count of the current fruit in the map
        map.set(fruits[r], (map.get(fruits[r]) || 0) + 1);

        // Check if there are more than 2 types of fruits
        while (map.size > 2) {
            // Decrease the count of the fruit at the left pointer
            map.set(fruits[l], map.get(fruits[l]) - 1);

            // If the count becomes 0, remove the fruit from the map
            if (map.get(fruits[l]) === 0) {
                map.delete(fruits[l]);
            }

            // Move the left pointer to the right
            l++;
        }

        // Update the answer with the maximum length
        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}

let fruits = [1, 2, 1];
console.log(totalFruit(fruits));
