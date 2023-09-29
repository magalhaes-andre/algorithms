/*
Stack is one of the core data structures to understand.
Approaching it with simplicity, a stack represents a structure where
the first item to enter this structure will be positioned last, and the following
will be positioned closer to the start. (FILO -> First In, Last Out)

Common APIs to use with Stacks:
- push(thingToAdd) -> add things to the stack
- pop() -> fetches the thing at the first position (top position, per say)
- peek() -> returns a reference of the thing at the top position, without removing it from the stack

 */
class Stack {

    private int capacity;
    private int topIndex;
    private int[] pile;
    public Stack(int capacity) {
        this.capacity = capacity;
        this.pile = new int[capacity];
        this.topIndex = -1;
    }

    public void push(int number) {
        if (isFull()) {
            throw new RuntimeException("Not able to push, stack is full");
        }
        topIndex++;
        pile[topIndex] = number;
    }

    public int pop() {
        if (isEmpty()){
            throw new RuntimeException("No value to pop, stack is empty");
        }
        return pile[topIndex--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("No value to peek, stack is empty");
        }
        return pile[topIndex];
    }

    private boolean isFull() {
        return topIndex == capacity - 1;
    }

    private boolean isEmpty() {
        return topIndex == -1;
    }
}

public class StackImplementation {

    public static void main(String[] args) {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.pop();
    }
}
