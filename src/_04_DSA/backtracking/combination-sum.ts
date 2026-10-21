/**
 * 
 * Example 1: 
   Input: candidates = [2,3,6,7], target = 7
    Output: [[2,2,3],[7]]
    Explanation:
    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
    7 is a candidate, and 7 = 7.
    These are the only two combinations.
    
    Example 2:

    Input: candidates = [2,3,5], target = 8
    Output: [[2,2,2,2],[2,3,3],[3,5]]
    
    Example 3:

    Input: candidates = [2], target = 1
    Output: []

 */
function combinationSum(candidates: number[], target: number): number[][] {
    const result: number[][] = [];

    function backtrack(start: number, target: number, path: number[]) {
        // console.log('backtrack is called', {start, target, path});
        
        if (target === 0) {
            result.push([...path]); // found a valid combination
            return;
        }

        for (let i = start; i < candidates.length; i++) {
            console.log({i});
            
            if (candidates[i] <= target) {
                // console.log('inside if', candidates[i]);
                
                path.push(candidates[i]);
                // console.log({path});
                
                // not i+1 because we can reuse same element
                backtrack(i, target - candidates[i], path);
                // console.log('------ pop operation ---');
                
                path.pop(); // backtrack
            }
        }
    }

    backtrack(0, target, []);
    return result;
}

const candidates = [2,3,6,7], target = 7
console.log(combinationSum(candidates, target));   // [[2,2,3],[7]]