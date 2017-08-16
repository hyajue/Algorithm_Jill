/**
* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
* 
* push(x) -- Push element x onto stack.
* pop() -- Removes the element on top of the stack.
* top() -- Get the top element.
* getMin() -- Retrieve the minimum element in the stack.
* Example:
* MinStack minStack = new MinStack();
* minStack.push(-2);
* minStack.push(0);
* minStack.push(-3);
* minStack.getMin();   --> Returns -3.
* minStack.pop();
* minStack.top();      --> Returns 0.
* minStack.getMin();   --> Returns -2.
*/

/*
复杂度
时间O(n) 空间O(n)
思路：双栈法
正常情况下top，pop和push操作都是常量时间的，而主要问题就在于这个getMin上面，如果遍历一遍去找最小值，那么getMin操作就是O(n)的，
容易想到追溯这个最小值，在push的时候维护最小值，但是如果pop出最小值如何获得第二小的值成了问题。如果要去寻找就不是常量时间了
解决的方案是再维护一个栈，称之为最小栈，如果遇到更小的值则插入最小栈，否则就不需要插入最小栈（这里正常栈是怎么都要插进去）。
这里的正确性在于，如果后来得到的值大于当前最小栈顶的值，那么接下来pop都会先出去，而最小栈顶的值会一直在，而当pop到最小栈顶的值时，
两个栈一起pop后接下来第二小的就在最小栈的顶端了。如此push时最多插入两个栈一个元素，是O(1)，top是取正常栈顶，O(1)，
而pop时也是最多抛出两个栈的栈顶元素，O(1)。最后getMin只需要peek最小栈顶栈顶即可，仍是O(1)，实现了所有操作的常量操作，空间复杂度是O(n)，最小栈的大小
*/

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        // x <= minStack.peek()!!!!!! 小于等于的都push进去
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    
    public void pop() {
        if(stack.isEmpty())
            return;
        int x = stack.pop();
        if(!minStack.isEmpty() && x == minStack.peek())
            minStack.pop();
    }
    
    public int top() {
        return stack.isEmpty() ? 0 : stack.peek();
    }
    
    public int getMin() {
        return minStack.isEmpty() ? 0 : minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */