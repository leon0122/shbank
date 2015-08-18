/**
 * 
 */
package com.huifu.utils.xml;

/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午3:09:35
 * @author jack.liu 
 */
public class XmlTemplete {

	/**
	 * 上海银行完整XML结构
	 * {0}表示head
	 * {1}表示body
	 */
	public static final String XML="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<service>"
			+"{0}{1}"
			+"</service>"
			+ "</xml>";
	
	/**
	 * HEAD模板
	 * {0}中包含BODY等其他内容
	 */
	public static final String HEAD="<head>{0}</head>";
	
	
	/**
	 * BODY模板
	 * {0}中包含FIELD等其他内容
	 */
	public static final String BODY="<body>{0}</body>";
	
	/**
	 * 单个属性模板
	 * {0}属性名
	 * {1}属性值
	 * <field name=" bosreqdate">20140318</field>
	 */
	public static final String FIELD="<field name=\"{0}\">{1}</field>";
	
	/**
	 * ARRAY模板
	 * {0} struct
	 */
	public static final String ARRAY="<array name=\"repdetarray\">{0}</array>";
	
	/**
	 * ARRAY模板
	 * {0} FIELD集合
	 */
	public static final String STRUCT="<struct>{0}</struct>";
}
