/*
    ⭐ Approach 1: Single Queue (Costly push) — Recommended
    💡 Intuition

    After pushing a new element:
        Rotate the queue so the new element comes to the front
        That way, pop() and top() are O(1)

    📌 Steps for push(x)
        Enqueue x
        Rotate previous elements behind it
*/

class MyStack {
    private q: number[];

    constructor() {
        this.q = [];
    }

    push(x: number): void {
        this.q.push(x);

        // rotate queue
        let size = this.q.length;
        while (size > 1) {
            this.q.push(this.q.shift()!);
            console.log(this.q)
            size--;
        }
    }

    pop(): number {
        return this.q.shift()!;
    }

    top(): number {
        return this.q[0];
    }

    empty(): boolean {
        return this.q.length === 0;
    }
}

const myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.push(3);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False


/*
    🔁 Approach 2: Two Queues (Costly pop)
*/