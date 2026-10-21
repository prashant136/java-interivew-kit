class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = val ?? 0
        this.next = next ?? null
    }
}

function removeNthFromEnd(head: ListNode | null, n: number): ListNode | null {
    const dummy = new ListNode(0, head);
    let fast: ListNode | null = dummy;
    let slow: ListNode | null = dummy;

    // Move fast n steps ahead
    for (let i = 0; i < n; i++) {
        fast = fast!.next;
    }

    // Move both until fast reaches last node
    while (fast!.next) {
        fast = fast!.next;
        slow = slow!.next;
    }

    // Remove the nth node from end
    slow!.next = slow!.next!.next;

    return dummy.next;
}

/*

    Why Do We Use a Dummy Node? (Real Intuition)
    Core problem:
        Sometimes the node to be removed is the HEAD itself.

    Linked lists are tricky because:
        You can’t go backward
        Removing a node requires access to the previous node

    
    Without Dummy Node → Problem Appears 😬 ::

        Example:
        1 → 2 → 3 → 4 → 5
        n = 5 (remove from end)

        This means:
        Remove node = 1 (HEAD)

        What happens?
        - There is no previous node before head
        - You need special logic:
            if (n == length) return head.next;


    Dummy Node = Fake Previous Node 🧠 ::

        We create:
        dummy → 1 → 2 → 3 → 4 → 5

        Now even the head 1 has a previous node → dummy


    ----------------------------------------------------------------
    Key Mental Model (This Is the Intuition 💡) ::
        Dummy node guarantees that every real node has a previous node.

    So:
        Head removal becomes normal removal
        No special case needed

    How Dummy Simplifies Logic -
    Without dummy:
        If removing head → special handling
        Else → normal removal

    🔥 With dummy:
        Always do:
        prev.next = prev.next.next

    One rule. Always works.


    
    Interview-Ready Explanation (Say This 🔥)

    “I use a dummy node so that even the head node has a previous pointer.
    This eliminates edge cases like deleting the head and allows uniform pointer manipulation using prev.next = prev.next.next.”
*/