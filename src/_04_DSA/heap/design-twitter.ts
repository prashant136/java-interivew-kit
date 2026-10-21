// https://leetcode.com/problems/design-twitter/description/
/**
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

    
    Implement the Twitter class :-

    - Twitter() Initializes your twitter object.
    - void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. 
        Each call to this function will be made with a unique tweetId.
    - List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. 
        Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
    - void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
    - void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
    

    Input :-
    ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
    [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
    Output :-
    [null, null, [5], null, null, [6, 5], null, [5]]

    Explanation :-
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
    twitter.follow(1, 2);    // User 1 follows user 2.
    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    twitter.unfollow(1, 2);  // User 1 unfollows user 2.
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    

    Constraints :-
    1 <= userId, followerId, followeeId <= 500
    0 <= tweetId <= 104
    All the tweets have unique IDs.
    At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
    A user cannot follow himself.
 */

// TweetNode for Linked List (storing tweet details)
class TweetNode {
    tweetId: number;
    time: number;
    next: TweetNode | null;

    constructor(tweetId: number, time: number, next: TweetNode | null = null) {
        this.tweetId = tweetId;
        this.time = time;
        this.next = next;
    }
}

// Min Heap implementation (custom for tweet comparison based on time)
class MaxHeap {
    private heap: TweetNode[];

    constructor() {
        this.heap = [];
    }

    private swap(i: number, j: number) {
        [this.heap[i], this.heap[j]] = [this.heap[j], this.heap[i]];
    }

    private heapifyUp(index: number) {
        while (index > 0) {
            const parent = Math.floor((index - 1) / 2);
            if (this.heap[parent].time < this.heap[index].time) {
                this.swap(parent, index);
                index = parent;
            } else break;
        }
    }

    private heapifyDown(index: number) {
        const length = this.heap.length;
        while (true) {
            let largest = index;
            const left = 2 * index + 1;
            const right = 2 * index + 2;

            if (left < length && this.heap[left].time > this.heap[largest].time) largest = left;
            if (right < length && this.heap[right].time > this.heap[largest].time) largest = right;

            if (largest === index) break;
            this.swap(index, largest);
            index = largest;
        }
    }

    public insert(node: TweetNode) {
        this.heap.push(node);
        this.heapifyUp(this.heap.length - 1);
    }

    public extractMax(): TweetNode | undefined {
        if (this.heap.length === 0) return undefined;
        if (this.heap.length === 1) return this.heap.pop();

        const max = this.heap[0];
        this.heap[0] = this.heap.pop()!;
        this.heapifyDown(0);
        return max;
    }

    public size(): number {
        return this.heap.length;
    }
}

class Twitter {
    private timeStamp: number;
    private userFollows: Map<number, Set<number>>;
    private userTweets: Map<number, TweetNode | null>;

    constructor() {
        this.timeStamp = 0;
        this.userFollows = new Map();
        this.userTweets = new Map();
    }

    // Ensure user follows themselves
    private ensureUser(userId: number) {
        if (!this.userFollows.has(userId)) {
            this.userFollows.set(userId, new Set([userId]));
        }
    }

    // Post a new tweet
    postTweet(userId: number, tweetId: number): void {
        this.ensureUser(userId);
        const newTweet = new TweetNode(tweetId, this.timeStamp++, this.userTweets.get(userId) || null);
        this.userTweets.set(userId, newTweet);
    }

    // Retrieve the 10 most recent tweet IDs in the user's news feed
    getNewsFeed(userId: number): number[] {
        const res: number[] = [];
        if (!this.userFollows.has(userId)) return res;

        const heap = new MaxHeap();
        const followedUsers = this.userFollows.get(userId)!;

        // Insert the head of each user's tweet list into the $04DSA.heap
        for (let user of followedUsers) {
            const tweetHead = this.userTweets.get(user);
            if (tweetHead) heap.insert(tweetHead);
        }

        // Extract up to 10 most recent tweets
        while (heap.size() > 0 && res.length < 10) {
            const node = heap.extractMax()!;
            res.push(node.tweetId);
            if (node.next) heap.insert(node.next);
        }

        return res;
    }

    // Follower follows a followee
    follow(followerId: number, followeeId: number): void {
        if (followerId === followeeId) return;
        this.ensureUser(followerId);
        this.ensureUser(followeeId);
        this.userFollows.get(followerId)!.add(followeeId);
    }

    // Follower unfollows a followee
    unfollow(followerId: number, followeeId: number): void {
        if (followerId === followeeId) return;
        if (this.userFollows.has(followerId)) {
            this.userFollows.get(followerId)!.delete(followeeId);
        }
    }
}

// ----------------------------------------
// 🧪 Example Usage:
// ----------------------------------------

const twitter = new Twitter();

twitter.postTweet(1, 5);               // User 1 posts tweet (id = 5)
console.log(twitter.getNewsFeed(1));   // [5]

twitter.follow(1, 2);                  // User 1 follows User 2
twitter.postTweet(2, 6);               // User 2 posts tweet (id = 6)
console.log(twitter.getNewsFeed(1));   // [6, 5]

twitter.unfollow(1, 2);                // User 1 unfollows User 2
console.log(twitter.getNewsFeed(1));   // [5]
