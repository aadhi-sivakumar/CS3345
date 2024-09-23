import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// ListStack class implements BKStack using a linked list structure.
// It also implements Iterable<Double> for easy traversal of stack elements.
public class ListStack implements BKStack, Iterable<Double> 
{
    private ListStackNode topNode; 
    private int size;
    // Tracks modifications to the stack for iterator fail-fast behavior              
    private int modCount = 0;      

    // Constructor initializes the linked list stack with no elements.
    // Time complexity: O(1).
    public ListStack() 
    {
        this.topNode = null; 
        this.size = 0;
    }

    // Checks if the stack is empty and returns true or false.
    // Time complexity: O(1).
    @Override
    public boolean isEmpty() 
    {
        return this.size == 0;
    }

    // Returns the total number of elements present in the stack by using an enhanced for loop.
    // Time complexity: O(n), where n is the number of elements.
    @Override
    public int count() 
    {
        int count = 0;
        for (Double element : this) 
        {
            count++;
        }
        return count;
    }

    // Adds a new element to the top of the stack by creating a new node.
    // The new node is linked as the top element, pushing the previous top down.
    // Time complexity: O(1).
    @Override
    public void push(double d) 
    {
        // Create a new node with the provided value
        ListStackNode newNode = new ListStackNode(d);
        // Links the new node to the previous top node
        newNode.next = this.topNode;
        //updates the top node to the new node
        this.topNode = newNode;
        // Increments the stack size
        this.size++;
        // Increment modification count after a structural change
        modCount++;  
    }

    // Removes and returns the element from the top of the stack.
    // Throws an EmptyStackException if the stack is empty.
    // Time complexity: O(1).
    @Override
    public double pop() 
    {
        if (isEmpty()) 
        {
            throw new EmptyStackException();
        }
        // Gets the value of the top element
        double poppedValue = this.topNode.getData();
        // Moves the top node reference to the next node
        this.topNode = this.topNode.next; 
        // Decrements the size of the stack
        this.size--;
        // Increments modification count after a structural change
        modCount++;  
        // Returns the pooped value
        return poppedValue;
    }

    // Returns the element at the top of the stack without removing it.
    // Throws an EmptyStackException if the stack is empty.
    // Time complexity: O(1).
    @Override
    public double peek() 
    {
        if (isEmpty()) 
        {
            throw new EmptyStackException();
        }
        return this.topNode.getData();
    }

    // Returns an iterator for traversing the stack.
    @Override
    public Iterator<Double> iterator() 
    {
        return new ListStackIterator();
    }

    // Inner class for the iterator to traverse the stack.
    private class ListStackIterator implements Iterator<Double> 
    {
        // Current node being iterated over
        private ListStackNode currentNode = topNode; 
        // To detect concurrent modifications 
        private final int expectedModCount = modCount;  

        // Checks if there is another element in the stack during iteration.
        // Time complexity: O(1).
        @Override
        public boolean hasNext() 
        {
            checkForConcurrentModification();
            // Returns true if there is another node to vist
            return currentNode != null;
        }

        // Returns the next element in the stack.
        // Throws NoSuchElementException if no more elements are left.
        // Time complexity: O(1).
        @Override
        public Double next() 
        {
            checkForConcurrentModification();
            if (!hasNext()) 
            {
                // No more elements to return
                throw new NoSuchElementException();
            }
            // Retrieve the current node's data
            Double data = currentNode.getData();
            // Move to the next node
            currentNode = currentNode.next; 
            // Return the retrieved data 
            return data;
        }

        // Checks if the stack has been modified during iteration.
        // If it has, it throws a ConcurrentModificationException
        private void checkForConcurrentModification() 
        {
            if (modCount != expectedModCount) 
            {
                throw new ConcurrentModificationException();
            }
        }
    }
}
