import java.util.Arrays;

/**
 * UMUC
 */

/**
 * @author Himel
 *
 */
public class BubbleSort implements SortInterface {

	private int count = 0;
	private long startTime = 0;
	private long endTime = 0;

	/**
	 * 
	 */
	public BubbleSort(int[] list, boolean recursive) {

		startTime = System.nanoTime(); // START TIME MEASUREMENT
		if (recursive) {
			recursiveSort(list);
		} else {
			iterativeSort(list);
		}
		endTime = System.nanoTime(); // START TIME MEASUREMENT

		try { // make sure it's really sorted
			assertSorted(list);
		} catch (UnsortedException e) {
			e.printStackTrace();
			System.out.println(Arrays.toString(list));
		}
	}

	@Override
	public void recursiveSort(int[] list) {
		recursiveBubbleSort(list, list.length);
	}

	public int[] recursiveBubbleSort(int[] list, int n) {
		// adapted from
		// http://www.xetoware.com/blog/recursive-bubble-sort-method-in-java

		if (n == 1) {
			return list; // finished sorting
		}

		int temp;
		for (int i = 0; i < n - 1; i++) {
			
			count += 1; // COUNT CRITICAL OPERATION
			if (list[i + 1] < list[i]) { // CRITICAL OPERATION
				temp = list[i];
				list[i] = list[i + 1];
				list[i + 1] = temp;
			}
		}
		return recursiveBubbleSort(list, n - 1); // n - 1 because last is
													// guaranteed sorted
	}

	@Override
	public void iterativeSort(int[] list) {
		// adapted from http://www.algolist.net/Algorithms/Sorting/Bubble_sort

		boolean swapped = true;

		int j = 0;

		int tmp;

		while (swapped) {

			swapped = false;

			j++;

			for (int i = 0; i < list.length - j; i++) {

				count += 1; // COUNT CRITICAL OPERATION
				if (list[i] > list[i + 1]) { // CRITICAL OPERATION

					tmp = list[i];

					list[i] = list[i + 1];

					list[i + 1] = tmp;

					swapped = true;

				}

			}

		}

	}

	private void assertSorted(int[] original) throws UnsortedException {
		int[] copy = Arrays.copyOf(original, original.length);

		// Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and
		// Joshua Bloch
		Arrays.sort(copy);

		boolean isSorted = Arrays.equals(original, copy);
		if (isSorted == false) {
			throw new UnsortedException();
		}
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public long getTime() {
		return endTime - startTime;
	}

}
