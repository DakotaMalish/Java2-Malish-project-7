import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

//code obtained from  the book THANKS BOOK!



public class SynchronizedBuffer implements Buffer 
{
    // Lock to control synchronization with this buffer 
    private final Lock accessLock = new ReentrantLock();

    // conditions to control reading and writing                 
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition(); 

    private int buffer = -1; // shared by producer and consumer threads
    private boolean occupied = false; // whether buffer is occupied
    KeyCount kCount = new KeyCount();

    // place int value into buffer
    @Override
    public void blockingPut(int value) throws InterruptedException 
    {
       accessLock.lock(); // lock this object

        // output thread information and buffer information, then wait
        try 
        {
            // while buffer is not empty, place thread in waiting state
            while (occupied) 
            {
                displayState();
                canWrite.await(); // wait until buffer is empty
            } 

            buffer = value; // set new buffer value

            // indicate producer cannot store another value
            // until consumer retrieves current buffer value
            occupied = true;


            // signal any threads waiting to read from buffer
            canRead.signalAll();                             
        } 
        finally 
        {
            accessLock.unlock(); // unlock this object
        }
    } 

    // return value from buffer
    @Override
    public int blockingGet() throws InterruptedException {
        int readValue = 0; // initialize value read from buffer
        accessLock.lock(); // lock this object

        // output thread information and buffer information, then wait
        try 
        {
            // if there is no data to read, place thread in waiting state
            while (!occupied) 
            {
                displayState();
                canRead.await(); // wait until buffer is full
            } 

            // indicate that producer can store another value 
            // because consumer just retrieved buffer value
            occupied = false;

            readValue = buffer; // retrieve value from buffer
            displayState();

            // signal any threads waiting for buffer to be empty
            canWrite.signalAll();                               
        } 
        finally 
        {
            accessLock.unlock(); // unlock this object
        } 
        return readValue;
    } 

    //switch the locks either off or on
    private void displayState() 
    {
        try 
        {
            accessLock.lock(); // lock this object
        }
        finally 
        {
            accessLock.unlock(); // unlock this objects
        }
    }
}