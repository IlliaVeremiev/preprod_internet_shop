package com.epam.preprod.veremiev.task11.core.timers;

import java.io.Serializable;

/**
 * Timered task. Process after selected time interval
 * 
 * @author Illia_Veremiev
 *
 */
public class TimeredTask implements Runnable, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5492605422328240801L;

	/**
	 * Time interval before task processing
	 */
	private long timeout;

	/**
	 * Processing thread
	 */
	private transient Thread thread;

	/**
	 * Processing task
	 */
	private Runnable task;

	/**
	 * Constructor with parameters
	 * 
	 * @param task - task to process
	 */
	public TimeredTask(Runnable task) {
		this.task = task;
	}

	/**
	 * Starts task processing after <b>millis</b> milliseconds. If use twice, will
	 * become impossible to cancel previous task
	 * 
	 * @param millis - time to wait in milliseconds
	 */
	public void startAfter(long millis) {
		timeout = millis;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			return;
		}
		task.run();
	}

	/**
	 * Try to cancel started task. Possible if time delay not end
	 */
	public void tryCancel() {
		thread.interrupt();
	}
}