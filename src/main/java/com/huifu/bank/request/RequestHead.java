/**
 * 
 */
package com.huifu.bank.request;

/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午2:57:31
 * @author jack.liu 
 */
public class RequestHead {

	private String bostrancode;
	
	private String bostranserno;
	
	private String bosreqsys;
	
	private String bosreqdate;
	
	private String bosreqtime;

	public String getBostrancode() {
		return bostrancode;
	}

	public void setBostrancode(String bostrancode) {
		this.bostrancode = bostrancode;
	}

	public String getBostranserno() {
		return bostranserno;
	}

	public void setBostranserno(String bostranserno) {
		this.bostranserno = bostranserno;
	}

	public String getBosreqsys() {
		return bosreqsys;
	}

	public void setBosreqsys(String bosreqsys) {
		this.bosreqsys = bosreqsys;
	}

	public String getBosreqdate() {
		return bosreqdate;
	}

	public void setBosreqdate(String bosreqdate) {
		this.bosreqdate = bosreqdate;
	}

	public String getBosreqtime() {
		return bosreqtime;
	}

	public void setBosreqtime(String bosreqtime) {
		this.bosreqtime = bosreqtime;
	}

}
