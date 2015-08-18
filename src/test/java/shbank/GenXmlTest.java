/**
 * 
 */
package shbank;

import com.huifu.bank.request.RequestHead;
import com.huifu.bank.request.bos01.BOS01Request;
import com.huifu.utils.DateUtil;
import com.huifu.utils.xml.GenBOSXML;

/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午4:18:50
 * @author jack.liu 
 */
public class GenXmlTest {

	public static void main(String[] args) {
		BOS01Request bos01=new BOS01Request();
		bos01.setBosidtp("10100");
		bos01.setBosidno("420684198101223516");
		bos01.setBoscunm("刘剑");
		bos01.setBosfrom("2");
		bos01.setBosmersys("0002");
		bos01.setBosbizscene("01");
	
		
		GenBOSXML gen=new GenBOSXML();

		RequestHead head=new RequestHead();
		head.setBostrancode("bos01");
		head.setBostranserno("21323232");
		head.setBosreqsys("1111");
		head.setBosreqdate(DateUtil.getDate());
		head.setBosreqtime(DateUtil.getTm());
		
		String fullXml=gen.packFullXml(head, bos01);
		System.out.println(fullXml);
	}
}
