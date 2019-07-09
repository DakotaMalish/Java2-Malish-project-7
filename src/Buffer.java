/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//code obtained from  the book THANKS BOOK!

public interface Buffer 
{  

    public void blockingPut(int i) throws InterruptedException;
    

    public int blockingGet() throws InterruptedException;
}
