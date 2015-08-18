/**
 * 
 */
package com.huifu.utils.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午2:52:32
 * @author jack.liu 
 */
@Target(ElementType.PARAMETER)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface Param {
	String value(); 
}
