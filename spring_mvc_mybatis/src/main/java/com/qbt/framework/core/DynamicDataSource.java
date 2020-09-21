package com.qbt.framework.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;

/**
 * <p>名称：DynamicDataSource.java</p>
 * <p>描述：根据不同的策略动态的切换数据源</p>
 * <pre>
 *    
 * </pre>
 * @author 鲍建明
 * @date 2014年8月24日 下午4:30:36
 * @version 1.0.0
 */
public class DynamicDataSource extends AbstractDataSource implements InitializingBean{

	
	
	protected static Logger logger = LogManager.getLogger(DynamicDataSource.class);
    
    private DataSource writeDataSource;
    private Map<String, DataSource> readDataSourceMap;
    
    
    private String[] readDataSourceNames;
    private DataSource[] readDataSources;
    private int readDataSourceCount;

    private AtomicInteger counter = new AtomicInteger(1);

    
    /**
     * 设置读库（name, DataSource）
     * @param readDataSourceMap
     */
    public void setReadDataSourceMap(Map<String, DataSource> readDataSourceMap) {
        this.readDataSourceMap = readDataSourceMap;
    }
    public void setWriteDataSource(DataSource writeDataSource) {
        this.writeDataSource = writeDataSource;
    }
    
    
    public void afterPropertiesSet() throws Exception {
        if(writeDataSource == null) {
            throw new IllegalArgumentException("property 'writeDataSource' is required");
        }
        if(MapUtils.isEmpty(readDataSourceMap)) {
            throw new IllegalArgumentException("property 'readDataSourceMap' is required");
        }
        readDataSourceCount = readDataSourceMap.size();
        
        readDataSources = new DataSource[readDataSourceCount];
        readDataSourceNames = new String[readDataSourceCount];
        
        int i = 0;
        for(Entry<String, DataSource> e : readDataSourceMap.entrySet()) {
            readDataSources[i] = e.getValue();
            readDataSourceNames[i] = e.getKey();
            i++;
        }
        
        
    }
    
    
    /**
     * 
     * <p>描述：设置数据源</p>
     * <pre>
     *    
     * </pre>
     * @return
     */
    private DataSource determineDataSource() {
    	//TODO 一开始重复调用。原因要设置默认数据源
    	
        if(DynamicDataSourceHolder.isWrite()) {
        	logger.debug("current determine write datasource");
            return writeDataSource;
        }
        
        if(DynamicDataSourceHolder.isNull()) {
        	logger.debug("no choice read/write, default determine write datasource");
            return writeDataSource;
        } 
        return determineReadDataSource();
    }
    
    private DataSource determineReadDataSource() {
        //按照顺序选择读库 
        //TODO 算法改进 
        int index = counter.incrementAndGet() % readDataSourceCount;
        if(index < 0) {
            index = - index;
        }
            
        String dataSourceName = readDataSourceNames[index];
        
        logger.debug("current determine read datasource : {}", dataSourceName);

        return readDataSources[index];
    }
    
    public Connection getConnection() throws SQLException {
        return determineDataSource().getConnection();
    }
    
    public Connection getConnection(String username, String password) throws SQLException {
        return determineDataSource().getConnection(username, password);
    }

	
	/*private DataSource writeDataSource;
	
	private Map<Object, Object> readDataSource;
	
	private Set<Object> readDataSourceName ;
	
	public void setWriteDataSource(DataSource writeDataSource) {
		this.writeDataSource = writeDataSource;
	}


	public void setReadDataSource(Map<Object, Object> readDataSource) {
		this.readDataSource = readDataSource;
	}


	@Override
	public void afterPropertiesSet() {
		if(writeDataSource == null){
			throw new IllegalArgumentException("Property 'writeDataSource' is required");
		}
		if(MapUtils.isEmpty(readDataSource)){
			throw new IllegalArgumentException("Property 'readDataSource' is required");
		}
		readDataSource.put(DynamicDataSourceHolder.DataSourceType.WRITE, writeDataSource);
		readDataSourceName = Sets.newHashSet(readDataSource.keySet());
		super.setTargetDataSources(readDataSource);
	}




	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("yyyyyyyyyyyyyyyyyyyyyy");
		
		//System.out.println(targetSources);
		
		
		switch (DynamicDataSourceHolder.getDataSourceType()) {
		case WRITE:
			System.out.println("write");
			break;
			//return DynamicDataSourceHolder.DataSourceType.WRITE;
		case READ:
			System.out.println("read");
			break;
		case DEFAULT:
			System.out.println("default");
			break;

		default:
			throw new IllegalArgumentException("未知数据源");
		}
		
		return DynamicDataSourceHolder.DataSourceType.WRITE;
	}*/

}
