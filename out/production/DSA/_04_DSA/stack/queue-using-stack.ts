class MyQueue {
    private stack1: number[];
    private stack2: number[];
    constructor() {
        this.stack1 = [];       // only push
        this.stack2 = [];       // used to poped and front
    }

    push(x: number): void {
        this.stack1.push(x);
    }

    pop(): number {
        // 👉 Move elements only if stack2 is empty
        if (this.stack2.length === 0) {
            while (this.stack1.length) {
                const popElement = this.stack1.pop();
                this.stack2.push(popElement);
            }
        }
        return this.stack2.pop();
    }

    peek(): number {
        if (this.stack1.length && this.stack2.length === 0) {
            while (this.stack1.length) {
                const popElement = this.stack1.pop();
                this.stack2.push(popElement);
            }
        }
        return this.stack2[this.stack2.length - 1];
    }

    empty(): boolean {
        return this.stack1.length === 0 && this.stack2.length === 0;
    }
}

const myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false