//test class of RadixSort
public class RadixSortTest
{
  //main method
   public static void main( String[] args )
   {
      //create object to perform radix sort
      RadixSort sortArray = new RadixSort( 20000, RadixSort.Order.ASCENDING );
      //records the start time of the sort
      long start = System.currentTimeMillis();
      //calls method radixSort to sort through the data
      sortArray.radixSort();
      //records the end time of the sort
      long end = System.currentTimeMillis();
      //outputs the difference in the start and end time, or the time it took to run the sort
      System.out.println ("Time: " + (end-start));
   } 
} 

