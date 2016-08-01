package com.perf.thread;

import com.perf.Perf;

public abstract class ControllThread extends Thread {
	protected Perf perf;

	public ControllThread() {
	}

	public ControllThread(Perf perf) {
		this.perf = perf;
	}

	public void setPerf(Perf perf) {
		this.perf = perf;
	}

	public Perf getPerf() {
		return perf;
	}
}
