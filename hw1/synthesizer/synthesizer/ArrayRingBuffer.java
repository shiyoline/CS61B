package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package

import java.util.Iterator;

/**
 * This program implements the 'array ring buffer' data structure,
 * which improves on the 'naive' bounded queue array.
 * @author  Thang Cao
 * @since   07/26/2020
 * */

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend synthesizer.AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] bufferData;

    /**
     * Create a new synthesizer.ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from synthesizer.AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        this.first = 0;
        this.last = 0;
        this.fillCount = 0; // protected variable from the synthesizer.AbstractBoundedQueue abstract class
        this.capacity = capacity;
        this.bufferData = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.

        // If the que is full, throw exception.
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        bufferData[last] = x;  // first item index = 0

        // Circular approach, set last equals to the front when reach the end of the array.
        if (last == capacity - 1){
            last = 0;
        } else {
            last++;
        }

        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T lastItem = bufferData[first];

        bufferData[first] = null;

        if (first == capacity - 1){
            first = 0;
        } else {
            first++;
        }

        fillCount--;

        return lastItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.

        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        if (first == capacity){
            first = 0;
            return bufferData[first];
        }

        return bufferData[first];

    }

    @Override
    public Iterator<T> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<T>{
        private int ptr;
        public KeyIterator() {
            ptr = 0;
        }

        public boolean hasNext() {
            return (ptr != fillCount);
        }

        public T next() {
            T returnItem = bufferData[ptr];
            ptr++;
            return returnItem;
        }

    }



    // TODO: When you get to part 5, implement the needed code to support iteration.
}
