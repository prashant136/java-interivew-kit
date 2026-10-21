class Stack<T> {
    private items: T[] = [];

    push(element: T): void {
        this.items.push(element);
    }

    pop(): T | undefined {
        return this.items.pop();
    }

    peek(): T | undefined {
        return this.items[this.items.length - 1];
    }

    size(): number {
        return this.items.length;
    }
}

class Queue<T> {
    private inStack: Stack<T> = new Stack<T>();
    private outStack: Stack<T> = new Stack<T>();

    enqueue(element: T): void {
        this.inStack.push(element);
    }

    dequeue(): T | undefined {
        this.shiftStacks();
        return this.outStack.pop();
    }

    peek(): T | undefined {
        this.shiftStacks();
        return this.outStack.peek();
    }

    size(): number {
        return this.inStack.size() + this.outStack.size();
    }

    private shiftStacks(): void {
        if (this.outStack.size() === 0) {
            while (this.inStack.size() > 0) {
                this.outStack.push(this.inStack.pop()!);
            }
        }
    }
}

// Example Usage:
const queue = new Queue<number>();

queue.enqueue(1);
queue.enqueue(2);
queue.enqueue(3);
console.log(queue.peek()); // 1
console.log(queue.dequeue()); // 1
console.log(queue.dequeue()); // 2
queue.enqueue(4);
console.log(queue.peek()); // 3
console.log(queue.dequeue()); // 3
console.log(queue.size()); // 1
