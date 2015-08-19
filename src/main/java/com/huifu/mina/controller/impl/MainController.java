package com.huifu.mina.controller.impl;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.huifu.mina.controller.IController;


@Component("mainController")
public class MainController implements IController{

	@Override
	public String processMessage(String inMsg) throws Exception {
		
		try {
			//为了避免产生多线程问题，所以变量都用局部变量
		
			//交易类型
			String transCode =null;
			String executeBeanName=null ;
			//构造返回包体的流
			ByteArrayOutputStream backStream=null;
			//返回报文转换规则
			String outRule = null;
			
			// 获得包头
		
//			transCode = header.getTxnCode();
	
			
			//获取该交易对应的beanName
//			executeBeanName=jbmapservice.getTransBean(transCode + "b2o").getServiceClass();
//			
//			IBussinessServiceInterface bussService = (IBussinessServiceInterface) SpringBeanUtil.getBean(executeBeanName);
//			
//			if (SpringBeanUtil.isSingleton(executeBeanName))
//				logger.error("for threads safety and performance,please don't use singleton mode for {}", executeBeanName);
//			else 
//				result = bussService.exceute(transCode, header, beanvo, null, inmessage.getMessagbody());
//
//			
//			// 成功和失败的转化代码是不同的
//			 if (result.isResultsuccess())
//		    	 outRule=jbmapservice.getTransBean(transCode + "b2o").getSuccessTransCode();
//		     else
//		    	 outRule=jbmapservice.getTransBean(transCode + "b2o").getFailTransCode();
//		     
//			// 返回包头
//			header.setTxnCode(outRule.substring(0,5));
//			backStream = jbmapservice.obj2Msg(header, headBackRule);
//			
//			//返回包体
//			String outMessage=bussService.body2Byte(jbmapservice, outMessage, backStream, result, outRule);
//			backStream.close();
			
//			return outMessage;
			return "hello"+inMsg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
