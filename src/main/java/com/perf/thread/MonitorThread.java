package com.perf.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.perf.Perf;

public class MonitorThread extends Thread {
	private Perf perf;
	private static Logger logger = LoggerFactory.getLogger(MonitorThread.class);
	public MonitorThread(Perf perf){
		this.perf = perf;
	}
}
