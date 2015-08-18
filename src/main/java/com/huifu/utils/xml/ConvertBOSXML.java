/**
 * 
 */
package com.huifu.utils.xml;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.huifu.bank.response.ResponseHead;
import com.huifu.bank.response.bos01.BOS01Response;

/**
 * 上海汇付金融服务有限公司
 * 2015年8月11日 上午11:11:16
 * @author jack.liu 
 */
public class ConvertBOSXML {

	public static Object convertXml(String xml, Object object) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Document document = DocumentHelper.parseText(xml);

		// 获取head

		ResponseHead rspHead = convertResponseHead(document);
		PropertiesUtils.setAttrValue(object, "responseHead", rspHead);
		
		// 获取body
		convertResponseBody(document, object);

		return object;
	}

	@SuppressWarnings("unchecked")
	private static void convertResponseBody(Document document, Object object) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 先将body下的field转换
		List<Element> fieldList = document.selectNodes("//body/field");
		for (Element element : fieldList) {
			String value = element.getText();
			String attr = ((Attribute) (element.attributes().get(0))).getValue();
			PropertiesUtils.setAttrValue(object, attr, value);
		}

		// 转换array下的struct
		Element array=(Element) document.selectSingleNode("//array");
		List<Element> structList = document.selectNodes("//array/struct");
		
		if(structList.size()==0)
			return;
		
		//获取返回报文中List对象类型
		String clazzType=PropertiesUtils.getListObject(object);
		List<Object> objList=new ArrayList<Object>();
		for (Element element : structList) {
			List<Element> sfList = element.selectNodes("field");
			
			// 获取每个strut中的内容
			Object obj=Class.forName(clazzType).newInstance();
			for (Element e : sfList) {			
				String value = e.getText();
				String attr = ((Attribute) (e.attributes().get(0))).getValue();
				PropertiesUtils.setAttrValue(obj, attr, value);
			}
			objList.add(obj);
		}
		
		String arrayName=((Attribute) (array.attributes().get(0))).getValue();
		PropertiesUtils.setAttrValue(object, arrayName, objList);
	}

	

	private static ResponseHead convertResponseHead(Document document) {
		ResponseHead rspHead = new ResponseHead();
		List<Element> list = document.selectNodes("//head/field");

		for (Element element : list) {
			String value = element.getText();
			String attr = ((Attribute) (element.attributes().get(0))).getValue();
			PropertiesUtils.setAttrValue(rspHead, attr, value);
		}
		return rspHead;
	}

	public static void main(String[] args) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" 
				+ "<service><head>" 
					+ "<field name=\"bostrancode\">XXXXXX</field>" 
					+ "<field name=\"bostranserno\">000000001</field>"
					+ "<field name=\"bosrepdate\">20140318</field>" 
					+ "<field name=\"bosreptime\">101445</field>" 
					+ "<field name=\"bosstatue\">S</field>" 
					+ "<field name=\"boserrorcode\"></field>"
					+ "<field name=\"boserrormsg\"></field></head>" 
				+ "<body>" 
							+ "<field name=\"billno\">12345678</field>" 
							+ "<field name=\"repamt\">2000.00</field>" 
					+ "<array name=\"repdetarray\">"
						+ "<struct>" + "<field name=\"billno\">12345678</field>" 
							+ "<field name=\"repamt\">2000.00</field>" 
							+ "<field name=\"curtype\">CNY</field>"
							+ "<field name=\"repdate\">20140115</field>" 
						+ "</struct>" 
						+ "<struct>" + "<field name=\"billno\">12345678</field>" 
							+ "<field name=\"repamt\">2000.00</field>"
							+ "<field name=\"curtype\">CNY</field>" 
							+ "<field name=\"repdate\">20140115</field>" 
						+ "</struct>"
				    + "</array></body></service>";

		ConvertBOSXML.convertXml(xml, new BOS01Response());
//		Object obj=Class.forName("com.huifu.bank.response.bos01.BOS01Struct");
	}
}
