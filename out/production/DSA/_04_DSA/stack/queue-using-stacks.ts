// 
class Queue {
    s1: number[]
    s2: number[]
    constructor() {
        this.s1 = [];
        this.s2 = [];
    }

    // Enqueue an item to the queue
    enqueue(x: number) {

        // Push item into the first $04DSA.stack
        this.s1.push(x);
    }

    // Dequeue an item from the queue
    dequeue() {

        // if both stacks are empty
        if (this.s1.length === 0 && this.s2.length === 0) {
            return -1;
        }

        // if s2 is empty, move
        // elements from s1
        if (this.s2.length === 0) {
            while (this.s1.length) {
                this.s2.push(this.s1.pop()!);
            }
        }

        // return the top item from s2
        let x = this.s2.pop();

        return x;
    }
}

function main() {
    let q = new Queue();

    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);

    console.log(q.dequeue());
    console.log(q.dequeue());
    // console.log(q.dequeue());

    q.enqueue(10);
    console.log(q.dequeue());
}

main();