/**
 * 
 */
package com.huifu.bank.response;

/**
 * 上海汇付金融服务有限公司 
 * 2015年8月7日 下午2:58:01
 * @author jack.liu
 */
public class ResponseHead {
	
	private String bostrancode;
	
	private String bostranserno;
	
	private String bosrepdate;
	
	private String bosreptime;
	
	private String bosstatue;
	
	private String boserrorcode;
	
	private String boserrormsg;

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

	public String getBosrepdate() {
		return bosrepdate;
	}

	public void setBosrepdate(String bosrepdate) {
		this.bosrepdate = bosrepdate;
	}

	public String getBosreptime() {
		return bosreptime;
	}

	public void setBosreptime(String bosreptime) {
		this.bosreptime = bosreptime;
	}

	public String getBosstatue() {
		return bosstatue;
	}

	public void setBosstatue(String bosstatue) {
		this.bosstatue = bosstatue;
	}

	public String getBoserrorcode() {
		return boserrorcode;
	}

	public void setBoserrorcode(String boserrorcode) {
		this.boserrorcode = boserrorcode;
	}

	public String getBoserrormsg() {
		return boserrormsg;
	}

	public void setBoserrormsg(String boserrormsg) {
		this.boserrormsg = boserrormsg;
	}

}
