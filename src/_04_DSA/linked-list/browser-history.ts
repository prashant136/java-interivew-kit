/*
        [Leetcode]
            |
          visit
            v
        [Google]
            |
          visit
            v
        [Facebook]
            |
          visit
            v
        [YouTube]


    ## Back / Forward Movements (From Facebook) -    
    back(1):   Facebook  ←  Google
    forward(1): Google   →  Facebook

    ## Extended Navigation with More History -
    [Leetcode] ⇄ [Google] ⇄ [Facebook] ⇄ [YouTube]
                         |
                       visit
                         v
                     [LinkedIn]

    ## Back Operations Explained -
    From Facebook -
        back(1) → Google
        back(2) → Leetcode

    From YouTube -
        back(1) → Facebook

    
    ## Forward Operations Explained -
    From Google -
        forward(1) → Facebook

    From LinkedIn -
        forward(2) → (not possible, stop at LinkedIn)

    ## Current Pointer View (Example) -
        [Leetcode] ⇄ [Google] ⇄ [Facebook] ⇄ [YouTube]
                              ↑
                           current

    - current points to the active page
    - back(k): move current ← k times (if prev exists)
    - forward(k): move current → k times (if next exists)
    - visit(newPage):
        current.next = null   // clear forward history
        newPage.prev = current
        current.next = newPage
        current = newPage
*/

class DoublyLinkedListNode {
    url: string
    prev: Node | null = null
    next: Node | null = null

    constructor(url: string) {
        this.url = url
    }
}

class BrowserHistory {
    private current: DoublyLinkedListNode

    constructor(homepage: string) {
        this.current = new DoublyLinkedListNode(homepage)
    }

    visit(url: string): void {
        const newNode = new DoublyLinkedListNode(url)

        // clear forward history
        this.current.next = null

        newNode.prev = this.current
        this.current.next = newNode
        this.current = newNode
    }

    back(steps: number): string {
        while (steps > 0 && this.current.prev) {
            this.current = this.current.prev
            steps--
        }
        return this.current.url
    }

    forward(steps: number): string {
        while (steps > 0 && this.current.next) {
            this.current = this.current.next
            steps--
        }
        return this.current.url
    }
}

const browser = new BrowserHistory("leetcode.com")

browser.visit("google.com")
browser.visit("facebook.com")
browser.visit("youtube.com")

browser.back(1)      // facebook.com
browser.back(1)      // google.com
browser.forward(1)   // facebook.com
browser.visit("linkedin.com")
browser.forward(2)   // linkedin.com
browser.back(2)      // google.com

