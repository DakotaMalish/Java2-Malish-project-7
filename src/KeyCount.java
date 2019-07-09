/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RedPanda13
 */
public class KeyCount {
    private String userInput = "";
    private String[] symbles = new String[26];
    private int[] keyCounts = new int[26];
    private int firstLocation, secondLocation, thirdLocation = 0;
    
    

    public void KeyCount(KeyCount Key)
    {
        this.userInput = Key.getWords();
        this.symbles = Key.getSymbles();
        this.keyCounts = Key.getCountsArray();
        this.firstLocation = Key.getFirst();
        this.secondLocation = Key.getSecond();
        this.thirdLocation = Key.getThird();
    }
    public int LetterValue(String s)
    {
        int value = -1;
        
        switch(s.toUpperCase())
        {
            case"A":value = 0;break;
            case"B":value = 1;break;                
            case"C":value = 2;break;                
            case"D":value = 3;break;
            case"E":value = 4;break;                
            case"F":value = 5;break;        
            case"G":value = 6;break;
            case"H":value = 7;break;                
            case"I":value = 8;break;        
            case"J":value = 9;break;
            case"K":value = 10;break;                
            case"L":value = 11;break;        
            case"M":value = 12;break;
            case"N":value = 13;break;                
            case"O":value = 14;break; 
            case"P":value = 15;break;
            case"Q":value = 16;break;                
            case"R":value = 17;break;        
            case"S":value = 18;break;
            case"T":value = 19;break;                
            case"U":value = 20;break;  
            case"V":value = 21;break;
            case"W":value = 22;break;                
            case"X":value = 23;break;        
            case"Y":value = 24;break;
            case"Z":value = 25;break; 
            default:break; 
        }       
        return value;
    }
    public void run()
    {
        int v = 0;
        for(int i = 0; i < userInput.length(); i ++)
        {
            v = LetterValue(Character.toString(userInput.charAt(i)));
            if(v >= 0){
                if(symbles[v] == null){symbles[v] = Character.toString(userInput.charAt(i));
                    keyCounts[v] = 1;
                }else{
                    if(keyCounts[v] >= 100){keyCounts[v] = 100;
                    }else{keyCounts[v]++;}}}}}
    
    
    public int getThirdCount(){return this.keyCounts[getThird()];}public int getTopThree(int[] c)
    {
        int first, second, third;
        first = second = third = -1;
        int total;
        for(int i = 0; i < c.length; i++)
        {
            if(c[i] > first)
            {
                third = second;
                second = first;
                first = c[i];
                this.thirdLocation = this.secondLocation;
                this.secondLocation = this.firstLocation;
                this.firstLocation = i;
            }
            else if(c[i]> second)
            {
                third = second;
                second = c[i];
                this.thirdLocation = this.secondLocation;
                this.secondLocation = i;
            }
            else if (c[i] > third)
            {
                third = c[i];
                this.thirdLocation = i;
            }
        }
        total = first + second + third;
        return total;
    }
    


   

        

    
    public void setCountsArray(int[] i){this.keyCounts = i;}
    public int[] getCountsArray(){return this.keyCounts;}    
    
    public void setThird(int i){this.thirdLocation = i;}
    public int getThird(){return this.thirdLocation;}
    
    public void setFirst(int i){this.firstLocation = i;}
    public int getFirst(){return this.firstLocation;}
        
    public void setWords(String s){this.userInput = s;}
    public String getWords(){return this.userInput;}   
    
    public void setSecond(int i){this.secondLocation = i;}
    public int getSecond(){return this.secondLocation;}
    
    public void setSymbles(String[] s){this.symbles = s;}
    public String[] getSymbles(){return this.symbles;}
    
    public int getFirstCount(){return this.keyCounts[getFirst()];}    
    public int getSecondCount(){return this.keyCounts[getSecond()];}    
   
}
