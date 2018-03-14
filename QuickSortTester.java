public class QuickSortTester {

     public static void main(String[] args) {
          // Tests

          String out = "";
          int[] test; //test array
          long time; //time, measured in nanoseconds
	  int arrays, size = 10; //change these variables to change number of arrays, size respectively

	  /*=================================
	  ~~~~~TESTER ALGO:~~~~~
	  For every single array leading up to 1000, a random value between 0 and 200 is given to each index. 
	  This ordered array is then shuffled using the method found in QuickSort, and the time is recorded. We then sort
	  the array with quicksort, record the time, and output the difference of the two times onto a csv file.
	  =================================*/
	  for ( arrays = 1000; arrays < 1501; arrays++) {

	    out = "";
	    time = 0;
	    test = new int[arrays]; //also can be substituted for size
	    for( int j = 0; j < test.length; j++ ) {
		test[j] = (int)( 201 * Math.random()); //each index is random number between 0 and 200
	    }
	    QuickSort.shuffle(test);

	    long start = System.nanoTime(); //start time

	    QuickSort.qSort( test, 0, test.length -1);

	    long end = System.nanoTime(); //end time

	    QuickSort.printArr(test);
	    System.out.println("Array size: " + arrays);

	    time = end - start;
	    time /= 500;
	    out += "Time was: " + time + " \n";

	    out += "\b";
	    System.out.println(out);   
	  }

     }
}
