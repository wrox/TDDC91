public class QSort {
  
	/**
	 * Quicksort the array a[] using m as cutoff to insertion sort.
	 */
	public static void quicksort(int[] a, int m) {
		quicksort(a, 0, a.length - 1, m);
	}

	public static int partition(int[] a, int low, int high) {
		int pivotIndex = (low + high) / 2;
		int pivotValue = a[pivotIndex]; // Få fram medianvärdet.
		
		while (high >= low) { // Från start till slut, while high är större än low
			while (a[low] < pivotValue) { // Hitta element i low som ska vara i high
				low += 1;
			}
			while (a[high] > pivotValue) { // Hitta element i high som ska vara i low
				high -= 1;
			}
			if (high >= low) { // Om high är större än low, swappa
				swap(a, low, high);
				low += 1;
				high -= 1;
			}
		}
		return low; 
	}
	
	/**
	 * Quicksort the subarray a[low .. high].
	 * Uses median-of-three partitioning
	 * and a cutoff to insertion sort of m.
	 */
	private static void quicksort(int[] a, int low, int high, int m) {
		if (high - low < m) { // Villkora användning av insertionsort
			insertionsort(a, low, high);
		} else { // Använd quick sort
			int index = partition(a, low, high); 
			if (low < index - 1) { // Sortera vänstra halvan
				quicksort(a, low, index - 1, m);
			}
			if (index < high) { // Sortera högra halvan
				quicksort(a, index, high, m);
			}
		}
	}

	/**
	 * Sort from a[low] to a[high] using insertion sort.
	 */
	private static void insertionsort(int[] a, int low, int high) {
        for (int i = low; i < (high + 1); i++) { // Medan i är mindre än high, i++
        	for (int j = i; j > low; j--) { // Medan j är större än low, gör j--
        		if (a[j-1] > a[j]) {
        			swap(a, j-1, j);
        		} else {
        			break;
        		}
        	}
        }

	}
	
	public static void swap(int[] array, int i, int j) {
	      int temp = array[i];
	      array[i] = array[j];
	      array[j] = temp;
	}
}
