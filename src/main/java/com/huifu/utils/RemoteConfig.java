package com.huifu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
/**
 * 读取remote配置文件信息
 * 上海汇付金融服务有限公司
 * 2015年4月17日 下午4:40:33
 * @author jack.liu
 */
public class RemoteConfig  implements FactoryBean<Object>{
    
    private static Properties props = null;
    
    private Resource configLocation;

	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	
	public void init() throws Exception {
		if (this.configLocation == null) {
			throw new IllegalArgumentException("configLocation is required");
		}

		loadConfig(configLocation);
	}
    
	/**
	 * 清除加载
	 */
    public static void clearProps(){
    	if(props != null){
    		props = null;
    	}
    }
    

    
   /**
    *  加载
    * @param configLocation
    */
    public void loadConfig(Resource configLocation) {
        if(props == null) {
            props = new Properties();
            
            InputStream fistream = null;
            
            try{
            	fistream = configLocation.getInputStream();
            	props.load(fistream);     	
            }catch(Exception e){	
            	e.printStackTrace();
            }finally{
            	if(fistream!=null)
            	{
            		try {		
						fistream.close();			
					} catch (IOException e) {	
						e.printStackTrace();
					}
            	}
            }
        }
    }
    
    public static String getString(String key) {
        return props.getProperty(key);
    }

	public Object getObject() throws Exception {
		return null;
	}

	public Class getObjectType() {
		return null;
	}

	public boolean isSingleton() {
		return false;
	}
}
