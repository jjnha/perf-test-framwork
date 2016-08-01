package com.perf.thread;


import java.util.concurrent.CountDownLatch;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PerfThread implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(PerfThread.class);
	protected long transCount = 0;
   // protected DataStatistics    statistics    = new DataStatistics();
	protected CountDownLatch countDownLatch;
	
	protected long overFlowCount = 1;
	protected long countIndex =0;
	
	protected long maxTips = -1;
	
	protected long minTips = 0;

	protected long sampleStart =0;
	
	protected boolean isAlive = true;
	protected boolean toDie =false;
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
