package gc_test;

import java.util.Arrays;

public class Producer extends Thread {
	private Queue queue = Queue.getInstance();
	
	@Override
	public void run() {
		try {
			double[] array = null;

			// allocated young region
			for (int i = 0; i < 20; i++) {
				array = sortArray();
			}
			
			// allocated old region
			queue.add(array);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * create double array
	 * sort
	 * @return 
	 */
	public double[] sortArray() {
		double[] array = null;

		int size = 2048 * 16;
		array = new double[size];

		for (int i = 0; i < array.length; i++) {
			array[i] = Math.random();
		}

		Arrays.sort(array);

		return array;
	}
}
