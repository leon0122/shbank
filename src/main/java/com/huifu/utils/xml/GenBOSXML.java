/**
 * 
 */
package com.huifu.utils.xml;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

import com.huifu.bank.request.RequestHead;

/**
 * 生成上海银行的XML
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午2:56:03
 * @author jack.liu 
 */
public class GenBOSXML {
	
	public String packFullXml(RequestHead head,Object body){
		String bodyXml =packBody(body);
		String headXml=packHead(head);
		return MessageFormat.format(XmlTemplete.XML, headXml,bodyXml);
	}

	private String packField(String field,Object value){
		return MessageFormat.format(XmlTemplete.FIELD, field,value);
	}
	
	private String packList(List<Object> value){
		StringBuffer struct=new StringBuffer("");
		for (Object object : value) {
			StringBuffer structBody=new StringBuffer("");
			List<String> fields=PropertiesUtils.getFiledName(object);
			for (String field : fields) 
				structBody.append(packField(field, PropertiesUtils.getFieldValue(field, object)));
			struct.append(MessageFormat.format(XmlTemplete.STRUCT, structBody.toString()));
		}
		
		return MessageFormat.format(XmlTemplete.ARRAY,struct);
	}
	
	private String packHead(RequestHead head){
		//循环取出该类的属性
		List<String> fields=PropertiesUtils.getFiledName(head);
		StringBuffer  body= new StringBuffer();
		for (String fieldName : fields) 
			body.append(packField(fieldName, PropertiesUtils.getFieldValue(fieldName, head)));
		
		return MessageFormat.format(XmlTemplete.HEAD, body.toString());
	}
	
	@SuppressWarnings("unchecked")
	private String packBody(Object object){
		//循环取出该类的属性
		Field[] fields=PropertiesUtils.getFileds(object);
		StringBuffer  body= new StringBuffer();
		for (Field field : fields) {
			//如果属性是list，则生成集合
			if(field.getType().toString().contains("java.util.List")){
				body.append(packList((List<Object>)PropertiesUtils.getFieldValue(field.getName(), object)));
			}
			else{
				body.append(packField(field.getName(), PropertiesUtils.getFieldValue(field.getName(), object)));			
			}
		
		}
		
		return MessageFormat.format(XmlTemplete.BODY, body.toString());
	}
}
