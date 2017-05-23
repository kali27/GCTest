package gc_test;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	private static Queue instance = null;
	private volatile List<double[]> queue = new ArrayList<double[]>();
	private int totalSize = 0;

	private Queue() {
	}

	// double check locking
	public static Queue getInstance() {
		if (instance == null) {
			synchronized (Queue.class) {
				if (instance == null) {
					instance = new Queue();
				}
			}
		}
		return instance;
	}

	public void add(double[] buf) {
		totalSize = totalSize + 1;
		queue.add(buf);
	}

	public void clear() {
		int goalSize = queue.size() / 3 ;

		for (int i = 0; i < goalSize * 2; i++) {
			queue.remove(0);
		}
	}

	public int getSize() {
		return queue.size();
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
