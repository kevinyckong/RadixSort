// Class that creates an array filled with random integers.  
// Provides methods to sort the array using the radix sort.

//import necessary classes
import java.util.Random;

public class RadixSort
{
  //creates variables
  private int[] data;
  private static Random generator = new Random();
  public static enum Order { RANDOM, ASCENDING, DESCENDING };
  
  //constructor that creates an array of given size and fill with random integers
  public RadixSort( int size )
  {
    this( size, Order.RANDOM );
  }
  
  //constructor that creates an array of given size and fill with integers in a specified order
  public RadixSort( int size, Order order )
  {
    //instantiates the array
    data = new int[ size ];
    
    //for loop to instantiate each element of the array
    for ( int i = 0; i < size; i++ ) 
    {
      //if the specified order is random
      if (order == Order.RANDOM) 
      {
        //assigns a randomly generated integer to the element
        data[ i ] = generator.nextInt();
      }
      //else if the specified order is ascending
      else if (order == Order.ASCENDING) 
      {
        //assigns the index of the element to the value of the element
        data[ i ] = i;
      }
      //else if the specified order is descending
      else 
      {
        //assigns the computed value to the element 
        data[ i ] = size - 1 - i; //ALWAYS POSITIVE AND LENGTH RESTRICTED
      }
    }
  }
  
  //sorting the array using the radix sort method
  public void radixSort()
  {
    //creates variables
    int bucket [][] = new int [20][data.length]; //creates 20 buckets to accommodate the possible digits
    int max = 0; //greatest number in the array
    int min = 0; //least number in the array
    
    //traverses the array to find the greates and least integers
    for (int i = 0; i < data.length; i ++)
    {
      if (data[i] > max)
      {
        max = data[i];
      }
      else if (data[i] < min)
      {
        min = data[i];
      }
    }
    
    //finds the longest integer of the least and greatest
    if (max < (int)Math.abs(min))
    {
      max = (int)Math.abs(min);
    }
    
    //finds the number of digits of the longest integer in the array
    int i = (int)Math.log10(max) + 1;
    
    //outter for loop, runs once for every digit of the longest integer
    for (int j = 0; j < i; j ++)
    {
      //creates an index to keep track of the number of integers thrown into each bucket
      //index[0] keeps track of the number of elements in bucket[0], and so on
      int [] index = new int [20];
      
      //this for loop will place each element into its respective bucket
      for (int k = 0; k < data.length; k ++)
      {
        //determines the digit being analyzed
        int digit = (data[k]%(int)Math.pow(10,j+1))/(int)Math.pow(10,j);
        //assigns the element into its respecatble bucket
        //bucket 0 contains -9, and so on
        bucket[digit+9][index[digit+9]] = data[k];
        //increases the index keeping track of the number of elements in the bucket
        index[digit+9]++;
      }
      //calls on the merge method to assemble the array back for next iteration
      data = merge(bucket, index);
    }
  }
  
  
  //this method will assemble the array back from each individual bucket
  public int[] merge (int[][] bucket, int[] index)
  {
    //creates variables
    int [] copy = new int [data.length]; //assembled array to be returned
    int element = 0; //the index of the returned array
    
    //for loop to go through each bucket, starting with bucket[0] representing digit -9
    for (int i = 0; i < bucket.length; i ++)
    {
      //for loop to go through all the elements stored in the bucket
      for (int j = 0; j< index[i]; j ++)
      {
        //assigns the element from the bucket to the returned array
        copy[element] = bucket[i][j];
        element ++;
      }
    }
    //return the assembled array
    return copy;
  }
  
  // method to output values in array
  public String toString()
  {
    String arrayContents = "";
    
    // iterate through array
    for ( int element : data )
      arrayContents += element + "  ";
    
    return arrayContents + "\n";
  } // end method toString
}

