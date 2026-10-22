package _01_CoreJava;

public class _9_hashmap {

}





/// ------------------------- hashmap internal --------------------------
/// inset, delete, search in hashmap - O(1) :: stores key, value pairs.
//example of key val pair - HashMap<Integer, Integer>
//                      HashMap<String, String>
//                      HashMap<String, Integer>
//                      HashMap<Employee, Integer>
//                      HashMap<Integer, Employee>

/// Internally hashmap is an array of linked list. Basically we declare an array whose initial capacity = 16 and in every element of array you can store a linked list.
/// hashmap used hashing { Hashing is a method which takes any input & convert it into some value called hash value }.

/// when two diff input produce same hash value, it called has collision. Lesser the collision, better the hash fucntion.

/// hashmap has its own hash() method. (we can give custom implementation)
/// Key is passed as an input in a hash function, the resultant hash value output is considered as the index where the key value pair will be stored. the integer value represnts the index of array where it stored the key value pair.
/// key ----> HASH FUNCTION ----> index of array

/// ab hum different key value pairs ko hashmap se insert krte hai. agar key same aa jaye toh collision hoga aur linked list form hogi us index pe.
/// in reality hum address store krte hai.

/// in insert operation, firstly hum hash function ki help se index find karenge. phir key value pair ko us index me insert kr denge.
/// in search operation, same cheeze key ko hash fn me pass krke index find karenge phir us index se value featch karenge.
/// in delete --> index find krke delete kr denge.

/*
 +-----------------------------------------------------------------------------------------+
 |                              HashMap Internal Implementation                            |
 |                                                                                         |
 |   [{"Shivam", 27}, {"Naman", 25}, {"Sachit", 19}, {"Manas", 31}, {"Pushp", 22}, ...]    |
 |                                                                                         |
 |    0 |                                                                                  |
 |    1 |                                                                                  |
 |    2 | {"Shivam", 27} ----> {"Pushp", 22} ----> {"Kunal", 29}                           |
 |    3 |                                                                                  |
 |    4 | {"Sumit", 31}                                                                    |
 |    5 |                                                                                  |
 |    6 |                                                                                  |
 |    7 | {"Naman", 25}  ----> {"Manas", 31}                                               |
 |    8 |                                                                                  |
 |    9 | {"Sachit", 19}                                                                   |
 |   10 |                                                                                  |
 |   11 |                                                                                  |
 |   12 |                                                                                  |
 |   13 |                                                                                  |
 |   14 |                                                                                  |
 |   15 |                                                                                  |
 +-----------------------------------------------------------------------------------------+
*/

/// Collision case -
/// suppose hum kunal search kr rhe hai. hash function ne index = 2 dia hai. At this index we have 3 diff. values
/// toh humein array index = 2 pe jake iterate krke ('kunal' as key) search krna padega. Yaha Time Complexity increase hoga - O(1+length of linked-list) hoga.
/// even further optimization after java 8, if to many key pairs land in the same index, linkedlist in that index automatically convert into a RB tree for faster lookups. Time Complexity - O(1+log(n)), n = number of nodes in tree
