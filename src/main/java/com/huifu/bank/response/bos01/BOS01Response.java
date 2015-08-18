/**
 * 
 */
package com.huifu.bank.response.bos01;

import java.util.List;

import com.huifu.bank.response.ResponseHead;


/**
 * 上海汇付金融服务有限公司
 * 2015年8月7日 下午2:29:54
 * @author jack.liu 
 */
public class BOS01Response {
	private ResponseHead responseHead;
	
	private List<BOS01Struct> repdetarray;

	public ResponseHead getResponseHead() {
		return responseHead;
	}

	public void setResponseHead(ResponseHead responseHead) {
		this.responseHead = responseHead;
	}

	public List<BOS01Struct> getRepdetarray() {
		return repdetarray;
	}

	public void setRepdetarray(List<BOS01Struct> repdetarray) {
		this.repdetarray = repdetarray;
	}
}
