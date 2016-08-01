package com.perf;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.midi.Sequence;
import javax.swing.SizeSequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.perf.thread.ControllThread;
import com.perf.thread.MonitorThread;
import com.perf.thread.PerfThread;
import com.perf.thread.PerfThreadFactory;
import com.perf.thread.TimeoutAbortThread;

public class Perf {
	private static final Logger logger = LoggerFactory.getLogger(Perf.class);
	private int threadPoolSize = 50;

	private int loopCount = 2000;
	private int interval = 1000;
	private long timeOut = -1;
	private long maxTps = -1;
	
	private PerfThreadFactory threadFactory = null;
	private MonitorThread monitorThread = null;
	private ControllThread controllThread = null;
	private TimeoutAbortThread timeOutThread = null;
	
	private ExecutorService threadPool = null;
	private CountDownLatch threadLatch = null;
	private List<PerfThread> threads = new ArrayList<PerfThread>();
	private String startInfo = "{}";
	private boolean running = false;
	
	private String extInfo = null;
	private ReentrantLock adjustTrantLock = new ReentrantLock();
	private List<PerfThread> dieThread = new ArrayList<PerfThread>();
	
	public Perf(){
		initThreadPool();
	}
	
	public Perf(int thread, int count){
		this.threadPoolSize = thread;
		this.loopCount = count;
		initThreadPool();
	}

	public Perf(int thread, int count , int interval){
		this.threadPoolSize = thread;
		this.loopCount = count;
		this.interval = interval;
		initThreadPool();
	}
	
	public Perf(int thread, int count , PerfThreadFactory threadFactory){
		this.threadPoolSize = thread;
		this.loopCount = count;
		this.threadFactory = threadFactory;
		initThreadPool();
	}
	
	public Perf(int thread, int count ,int interval, PerfThreadFactory threadFactory){
		this.threadPoolSize = thread;
		this.loopCount = count;
		this.interval = interval;
		this.threadFactory = threadFactory;
		initThreadPool();
	}
	
	public void start(PerfThreadFactory threadFactory){
		this.threadFactory  = threadFactory;
		start();
	}
	
	protected void start(){
		for(int i = 0; i > threadPoolSize; i++){
			PerfThread thread = createThread();
			if(threads.contains(thread)){
				logger.error("the thread is exited");
				return;
			}
			
		}
	}

	protected PerfThread setPerThread(){
		PerfThread thread;
	}
	public PerfThread createThread(){
		PerfThread thread;
		if(null != threadFactory)
			return threadFactory.newThread();
		else
			thread = new PerfThread();
		return thread;
	}
	
	protected void initThreadPool() {
		// TODO Auto-generated method stub
		threadPool = new ThreadPoolExecutor(threadPoolSize, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
		new SynchronousQueue<Runnable>());
		threadLatch = new CountDownLatch(threadPoolSize);
		if(null == monitorThread){
			monitorThread = new MonitorThread(this);
		}
	}
}
