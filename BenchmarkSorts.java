import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.math.stat.descriptive.SummaryStatistics;

/**
 * 
 */

/**
 * @author Himel
 *
 */
public class BenchmarkSorts {

	private ArrayList<ArrayList<int[]>> runsOfDifferentSizesSet = new ArrayList<>();
	private int[] sizes;
	private Object[][] results;
	private Random randomGenerator = new Random();
	
	/**
	 * @param sizes - expects an array of size integers
	 */
	public BenchmarkSorts(int[] sizes) {
		this.sizes = sizes;
		this.results = new Object[sizes.length][9];
		
		int randomInt;
		for (int size: sizes) {  // size is also n
			ArrayList<int[]> runsSet = new ArrayList<>();
			for (int run=0; run<50; run++){
				int[] dataSet = new int[size];
				for (int n=0; n<size; n++){
					randomInt = randomGenerator.nextInt(1000000);
					dataSet[n] =randomInt;
				}
				runsSet.add(dataSet);
			}
			runsOfDifferentSizesSet.add(runsSet);
		}
	}
	
	public void runSorts() {
		

		for (int i = 0; i<runsOfDifferentSizesSet.size(); i++) {
			
			ArrayList<int[]> runsSet = runsOfDifferentSizesSet.get(i);
			int currentSize = sizes[i];
			
			SummaryStatistics iterativeCriticalOperationCounts = new SummaryStatistics();
			SummaryStatistics iterativeExecutionTimes = new SummaryStatistics();
			SummaryStatistics recursiveCriticalOperationCounts = new SummaryStatistics();
			SummaryStatistics recursiveExecutionTimes = new SummaryStatistics();

			for (int[] dataSet: runsSet) {
				int[] copy = Arrays.copyOf(dataSet, dataSet.length);
				
				BubbleSort iterative = new BubbleSort(dataSet, false);
				iterativeCriticalOperationCounts.addValue((iterative.getCount()));
				iterativeExecutionTimes.addValue(iterative.getTime());
				
				BubbleSort recursive = new BubbleSort(copy, true);
				recursiveCriticalOperationCounts.addValue(recursive.getCount());
				recursiveExecutionTimes.addValue(recursive.getTime());
			}
			
			results[i][0] = currentSize;
			results[i][1] = (int)iterativeCriticalOperationCounts.getMean();
			results[i][2] = (int)iterativeCriticalOperationCounts.getStandardDeviation();
			results[i][3] = (int)iterativeExecutionTimes.getMean();
			results[i][4] = (int)iterativeExecutionTimes.getStandardDeviation();
			results[i][5] = (int)recursiveCriticalOperationCounts.getMean();
			results[i][6] = (int)recursiveCriticalOperationCounts.getStandardDeviation();
			results[i][7] = (int)recursiveExecutionTimes.getMean();
			results[i][8] = (int)recursiveExecutionTimes.getStandardDeviation();
		}
	}
	

	public void displayReport() {
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Object columnNames[] = {
    		"n",
    		"ITERATIVE Average Critical Operation Count", 
    		"ITERATIVE Standard Deviation n of Count", 
    		"ITERATIVE Average Execution time",
    		"ITERATIVE Standard Deviation n of Time",
    		"RECURSIVE Average Critical Operation Count", 
    		"RECURSIVE Standard Deviation n of Count", 
    		"RECURSIVE Average Execution time",
    		"RECURSIVE Standard Deviation n of Time" 
	    };
	    
	    JTable table = new JTable(results, columnNames);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.pack();
	    frame.setVisible(true);
	}

}
