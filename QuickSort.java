// Team baconEggNCheese -- Aaron Li, George Liang, Jackie Li
// APCS2 pd8
// HW18 -- So So Quick
// 2018-03-12

/*****************************************************
 * class QuickSort
 * Implements quicksort algo to sort an array of ints in place
 *
 * 1. Summary of QuickSort algorithm:
 * qSort(arr, left, right):
     Start partitioning array based on the rightmost element. This element will be
     placed in its FRP. Then, start from the left bound and sort each element to its
     FRP using recursion in qSort. Then, start from pivot + 1 till the end of the array
     since pivot is already in the FRP, and repeat the recursion, except this time it is
     for the right side of the pivot.
     *
     * 2a. Worst pivot choice and associated runtime:
     *         When the pivot is the largest or smallest element, since either the right or
          left side of the pivot will be size 0. If successive calls also yield a 0 in
          the partition sizes, this will lead to an O(n^2) runtime.

	  * 2b. Best pivot choice and associated runtime:
	  *         When the pivot is the median of the array since this will partition the array
          into nearly equal parts. This case will lead to an O(n) runtime, since you will
          only need to iterate n times before all swaps are made and the array is sorted

	  * 3. Approach to handling duplicate values in array:
	  *         I allowed swapping of duplicates during the iteration of the partition. I set
          the condition for swapping to be less than OR EQUAL TO the pivot. This allows
          duplicates to be swapped and iterated over.

*****************************************************/

public class QuickSort
{
    //--------------v  HELPER METHODS  v--------------
    //swap values at indices x, y in array o
    public static void swap( int x, int y, int[] o ) {
	int tmp = o[x];
	o[x] = o[y];
	o[y] = tmp;
    }

    //print input array
    public static void printArr( int[] a ) {
	for ( int o : a )
	    System.out.print( o + " " );
	System.out.println();
    }

    //shuffle elements of input array
    public static void shuffle( int[] d ) {
	int tmp;
	int swapPos;
	for( int i = 0; i < d.length; i++ ) {
	    tmp = d[i];
	    swapPos = i + (int)( (d.length - i) * Math.random() );
	    swap( i, swapPos, d );
	}
    }

    //return int array of size s, with each element fr range [0,maxVal)
    public static int[] buildArray( int s, int maxVal ) {
	int[] retArr = new int[s];
	for( int i = 0; i < retArr.length; i++ )
	    retArr[i] = (int)( maxVal * Math.random() );
	return retArr;
    }

    // partitions array into two parts using a pivot point
    // @param left: lowerbound index
    // @param -- right: upperbound index
    // returns: number of elements smaller than the pivot
    public static int partition(int[] arr, int left, int right) {
	// start at the end and go to beginning
	int pivot = arr[right];
	// used to hold position for swap indices, start at left - 1
	int pos = left - 1;

	// iterate from the left till the right
	for (int i = left; i < right; i++ ) {
	    // starts from left: goes to right. Each element <= the pivot
	    // will be swapped with the leftmost element
	    if(arr[i] <= pivot) {
		pos += 1; // swap index, the initialized -1 will be incremented by 1 to 0, and so on
		swap(i, pos, arr); // swap em
	    }
	} swap(pos + 1, right, arr); // pivot element gets put at its appropriate location
	//System.out.println("Pivot: " + arr[pos + 1] + "\n"); // shows the pivot element used at its new index
	//printArr(arr); // show us the partitioned array
	return (pos + 1); // returns the number of elements smaller than the pivot
    }
    //--------------^  HELPER METHODS  ^--------------


    /*****************************************************
     * void qSort(int[])
     * @params d -- array of ints to be sorted in place
     * @params left -- left bound index
     * @params right -- right bound index
     * result: array will be sorted in ascending order

     * Mr. Brown gave us this code!
     *****************************************************/
    public static void qSort( int[] d, int left, int right )
    {
	if (left < right) { // you only continue to recurse until the bounds equal to each other
	    int index = partition(d, left, right);
	    qSort(d, left, index - 1); // this sorts the left bound side of the pivot index
	    qSort(d, index + 1, right); // this sorts the right bound side of the pivot index
	}
    }

    //main method for testing
    public static void main( String[] args )
    {
	int[] test;
	String out = "";
	long time;
	int arrays, size = 10; //change these variables to change number of arrays, size respectively
	for ( arrays = 0; arrays < 1000; arrays++) {

	    out = "";
	    time = 0;
	    test = new int[arrays]; //also can be substituted for size
	    for( int j = 0; j < test.length; j++ ) {
		test[j] = (int)( 51 * Math.random());
	    }
	    shuffle(test);

	    long start = System.nanoTime();

	    qSort( test, 0, test.length -1);

	    long end = System.nanoTime();
	    
	    printArr(test);

	    time = end - start;
	    time /= 50;
	    out += "Time was: " + time + " \n";

	    out += "\b";
	    System.out.println(out);   
	}
	/*
	//get-it-up-and-running, static test case:
	int [] arr1 = {7,1,5,12,3};
	System.out.println("\n\narr1 init'd to: " );
	printArr(arr1);

	qSort( arr1, 0, arr1.length - 1);
	System.out.println("\narr1 after qSort: " );
	printArr(arr1);

	// randomly-generated arrays of n distinct vals
	int[] arrN = new int[10];
	for( int i = 0; i < arrN.length; i++ )
	    arrN[i] = i;

	System.out.println("\n\narrN init'd to: " );
	printArr(arrN);

	shuffle(arrN);
	System.out.println("arrN post-shuffle: " );
	printArr(arrN);

	qSort( arrN, 0, arrN.length - 1 );
	System.out.println("\narrN after sort: " );
	printArr(arrN);


	//get-it-up-and-running, static test case w/ dupes:
	int [] arr2 = {7,1,5,12,3,7};
	System.out.println("\n\narr2 init'd to: " );
	printArr(arr2);

	qSort( arr2, 0, arr2.length - 1 );
	System.out.println("\narr2 after qSort: " );
	printArr(arr2);


	// arrays of randomly generated ints
	int[] arrMatey = new int[20];
	for( int i = 0; i < arrMatey.length; i++ )
	    arrMatey[i] = (int)( 48 * Math.random() );

	System.out.println("\n\narrMatey init'd to: " );
	printArr(arrMatey);

	shuffle(arrMatey);
	System.out.println("arrMatey post-shuffle: " );
	printArr(arrMatey);

	qSort( arrMatey, 0, arrMatey.length - 1 );
	System.out.println("\narrMatey after sort: " );
	printArr(arrMatey);
	*/
    }//end main

}//end class QuickSort
