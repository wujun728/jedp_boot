/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.bean</p>
 * <p>File: IdWorker.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年9月6日-上午10:06:17</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**<p>Class: IdWorker.java</p>
 * <p>Description: </p>
 * <pre>
 *         分布式ID生成策略
 * </pre>
 * @author 鲍建明
 * @date 2015年9月6日 上午10:06:17
 * @version 1.0.0
 */
public class IdWorker {
	protected static Logger logger = LogManager.getLogger(IdWorker.class);
	     
	    private long workerId;
	    private long datacenterId;
	    private long sequence = 0L;
	 
	    private long twepoch = 1288834974657L;
	 
	    private long workerIdBits = 5L;
	    private long datacenterIdBits = 5L;
	    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	    private long sequenceBits = 12L;
	 
	    private long workerIdShift = sequenceBits;
	    private long datacenterIdShift = sequenceBits + workerIdBits;
	    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	    private long sequenceMask = -1L ^ (-1L << sequenceBits);
	 
	    private long lastTimestamp = -1L;
	 
	    /**
	     * 
	     *  <pre>
	     *	</pre>
	     * @param workerId 应用的workId
	     * @param datacenterId 应用的内存的sequence
	     */
	    public IdWorker(long workerId, long datacenterId) {
	        // sanity check for workerId
	        if (workerId > maxWorkerId || workerId < 0) {
	            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
	        }
	        if (datacenterId > maxDatacenterId || datacenterId < 0) {
	            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
	        }
	        this.workerId = workerId;
	        this.datacenterId = datacenterId;
	        logger.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
	    }
	 
	    public synchronized long nextId() {
	        long timestamp = timeGen();
	 
	        if (timestamp < lastTimestamp) {
	        	logger.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
	            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
	        }
	 
	        if (lastTimestamp == timestamp) {
	            sequence = (sequence + 1) & sequenceMask;
	            if (sequence == 0) {
	                timestamp = tilNextMillis(lastTimestamp);
	            }
	        } else {
	            sequence = 0L;
	        }
	 
	        lastTimestamp = timestamp;
	 
	        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	    }
	 
	    protected long tilNextMillis(long lastTimestamp) {
	        long timestamp = timeGen();
	        while (timestamp <= lastTimestamp) {
	            timestamp = timeGen();
	        }
	        return timestamp;
	    }
	 
	    protected long timeGen() {
	        return System.currentTimeMillis();
	    }
	    
	    
	    /*public static void main(String[] args) {
	        Set<Long> set = new HashSet<Long>();
	        final IdWorker idWorker1 = new IdWorker(0, 0);
	        System.out.println(idWorker1.nextId());
	    }*/
}
