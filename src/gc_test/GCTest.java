package gc_test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCTest {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(GCTest.class);
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(7);

		// 1. set runtime
		Scanner scan = new Scanner(System.in);
		System.out.print("run time (minutes) : ");
		int runTime = scan.nextInt();

		logger.info("start time : " + currentTime());

		// 2. close when runtime is over.
		Runnable timer = new Runnable() {
			@Override
			public void run() {
				executorService.shutdown();

				logger.info("** interrupt ** ");
				logger.info("total size : " + Queue.getInstance().getTotalSize());
				logger.info("end time : " + currentTime());
			}
		};

		// 3. timer run
		executorService.schedule(timer, runTime, TimeUnit.MINUTES);
		
		// 4. producer run
		for (int i = 0; i < 5; i++) {
			executorService.scheduleWithFixedDelay(new Producer(), 0, 1, TimeUnit.MILLISECONDS);
		}
		
		// 5. consumer run
		executorService.scheduleWithFixedDelay(new Consumer(), 0, 1, TimeUnit.MILLISECONDS);
	}

	/**
	 * print current time
	 * 
	 * @return current time
	 */
	public static String currentTime() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strTime = dayTime.format(new Date(time));

		return strTime;
	}
}