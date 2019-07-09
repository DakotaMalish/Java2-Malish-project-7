


//code obtained from  the book THANKS BOOK!

public class Producer implements Runnable 
{
    private final Buffer sharedLocation; // reference to shared object
    KeyCount Key = new KeyCount();

    // constructor
    public Producer(Buffer sharedLocation, KeyCount kCounts) 
    {
        this.sharedLocation = sharedLocation;
        Key.setSymbles(kCounts.getSymbles());
        Key.setCountsArray(kCounts.getCountsArray());
        Key.setFirst(kCounts.getFirst());
        Key.setSecond(kCounts.getSecond());
        Key.setThird(kCounts.getThird());
    } 

    //Calculate the counts and store the sum of the highest 3 in the buffer
    @Override
    public void run() 
    {    
        try 
            { 
            int v = 0;
            for(int i = 0; i < Key.getWords().length(); i ++)
            {v = Key.LetterValue(Character.toString(Key.getWords().charAt(i)));
                if(v >= 0){
                    if(Key.getSymbles()[v] == null){Key.getSymbles()[v] = Character.toString(Key.getWords().charAt(i));
                        Key.getCountsArray()[v] = 1;
                    }else{
                        if(Key.getCountsArray()[v] >= 100){Key.getCountsArray()[v] = 100;}
                        else{Key.getCountsArray()[v]++;}}}}
            
            sharedLocation.blockingPut(Key.getTopThree(Key.getCountsArray())); // set value in buffer
        } 
        catch (InterruptedException exception) 
        {
        Thread.currentThread().interrupt();
        } 

    } 
} 