import java.util.EmptyStackException;

// ArrayStack class implements BKStack using an array structure.
public class ArrayStack implements BKStack 
{
    // Initial capacity of the stack array is set to 10
    private final int INITIAL_CAPACITY = 10;
    private double[] stackElements;
    // Index of the next available position in the stack.
    private int size = 0; 

    // Constructor initializes the stack with the initial capacity.
    // Time complexity: O(1).
    public ArrayStack() 
    {
        this.stackElements = new double[INITIAL_CAPACITY];
        this.size = 0;
    }

    // Checks if the stack is empty and returns true or false.
    // Time complexity: O(1).
    @Override
    public boolean isEmpty() 
    {
        return this.size == 0;
    }

    // Returns the total number of elements currently in the stack. 
    // Time complexity: O(1)
    @Override
    public int count() 
    {
        return this.size;
    }

    // Pushes a new element onto the stack.
    // If the array is full, it resizes the array to double its previous size.
    // Time complexity: O(1) in general, but O(n) when resizing is required.
    @Override
    public void push(double d) 
    {
        if (size == stackElements.length) 
        {
            resizeArray();
        }
        stackElements[size++] = d;
    }

    // Pops the top element off the stack and returns it.
    // If the stack is empty, throws an EmptyStackException.
    // Time complexity: O(1).
    @Override
    public double pop() 
    {
        if (isEmpty()) 
        {
            throw new EmptyStackException();
        }
        double poppedValue = stackElements[--size];
        stackElements[size] = 0;
        return poppedValue;
    }

    // Returns the top element of the stack without removing it.
    // If the stack is empty, throws an EmptyStackException.
    // Time complexity: O(1).
    @Override
    public double peek() 
    {
        if (isEmpty()) 
        {
            throw new EmptyStackException();
        }
        return stackElements[size - 1];
    }

    // Expands the capacity of the array if needed to accommodate more elements.
    // It creates a new array with double the capacity and copies over the elements from the old array.
    // Time complexity: O(n), where n is the number of elements being copied.
    private void resizeArray() 
    {
        double[] newArray = new double[stackElements.length * 2];
        System.arraycopy(stackElements, 0, newArray, 0, stackElements.length);
        stackElements = newArray;
    }
}
