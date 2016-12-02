public class SortMain {
	
	public static void main(String args[]) {
		int[] mySizes = { 10, 25, 50, 100, 250, 500, 1000, 2500, 5000, 10000 };
		BenchmarkSorts benchmark = new BenchmarkSorts(mySizes);
		benchmark.runSorts();
		benchmark.displayReport();
	}
}