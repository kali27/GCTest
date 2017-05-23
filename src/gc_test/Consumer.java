package gc_test;

public class Consumer extends Thread {
	private Queue queue = Queue.getInstance();

	@Override
	public void run() {
		// queue size for Out of memory  = 12181  (Xmx6g)
		if(queue.getSize() > 12000){
			queue.clear();
		}
	}
}
