// 👋 check anagrma
// function checkAnagram(str1, str2) {
//     const sortedStr1 = str1.split("").sort().join("");
//     const sortedStr2 = str2.split("").sort().join("");
//     return sortedStr1 === sortedStr2;
// }

// let str1 = "";
// let str2 = "";
// console.log(checkAnagram(str1, str2));

// 👀 Important valid anagram
function checkAnagram(str1: string, str2: string): boolean {
    const map = new Map();

    for(let ch of str1) {
        map.set(ch, (map.get(ch) || 0) + 1);
    }

    for(let ch of str2) {
        if(!map.has(ch)) {
            return false;
        }

        if(map.has(ch)) {
            map.set(ch, (map.get(ch) || 0) - 1);
        }
    }

    for(let [key, value] of map) {
        if(value !== 0) {
            return false;
        }
    }
    return true;
}
// let str1 = "anagram";
// let str2 = "nagaram";
// let str1 = "a";
// let str2 = "ab";
// console.log(checkAnagram(str1, str2));


function countAnagram(str: string, pattern: string): number {
    let i = 0;
    let result = 0;

    for (let j = 0; j < str.length; j++) {
        // window size is exceeding
        if (j - i + 1 > pattern.length) {
            i++;
        }

        // if window size is eqaul
        if (j - i + 1 === pattern.length) {
            if(checkAnagram(str.substring(i, j+1), pattern)) result++;
        }
    }

    return result;
}

const txt = "forxxorfxdofr";
const pat = "for";
console.log(countAnagram(txt, pat));


