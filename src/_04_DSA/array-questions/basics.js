//todo --------- Set to Array in JavaScript --------------

// 1. Using spread operator -
// const set = new Set(["foo", "bar", "baz", "foo"]);
// const arr = [...set];  -> // [ "foo", "bar", "baz" ]

// 2. Using Array.from() -
// const s = new Set([1, 1, 2, 3, 4, 4, 5, 6, 5]);
// let a = Array.from(s);  -> [[1, 2, 3, 4, 5, 6]]

// 3. Using Lodash _.toArray() -
// const _ = require("lodash");
// let s = new Set(['welcome', 'to', 'GFG']);
// console.log(_.toArray(s));

