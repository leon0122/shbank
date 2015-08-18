/**
 * 
 */
package com.huifu.bank.request.bos01;


/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午2:29:54
 * @author jack.liu 
 */
public class BOS01Request {

	private String bosidtp;

	private String bosidno;

	private String boscunm;

	private String bosfrom;

	private String bosmersys;

	private String bosbizscene;

	public String getBosidtp() {
		return bosidtp;
	}

	public void setBosidtp(String bosidtp) {
		this.bosidtp = bosidtp;
	}

	public String getBosidno() {
		return bosidno;
	}

	public void setBosidno(String bosidno) {
		this.bosidno = bosidno;
	}

	public String getBoscunm() {
		return boscunm;
	}

	public void setBoscunm(String boscunm) {
		this.boscunm = boscunm;
	}

	public String getBosfrom() {
		return bosfrom;
	}

	public void setBosfrom(String bosfrom) {
		this.bosfrom = bosfrom;
	}

	public String getBosmersys() {
		return bosmersys;
	}

	public void setBosmersys(String bosmersys) {
		this.bosmersys = bosmersys;
	}

	public String getBosbizscene() {
		return bosbizscene;
	}

	public void setBosbizscene(String bosbizscene) {
		this.bosbizscene = bosbizscene;
	}

}
