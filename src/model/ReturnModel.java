package model;


import org.dom4j.Element;

public class ReturnModel  {
	
	private String TrxId;
	private String TrxFinishTm;
	private float TrxAmt;
	private String SysRtnCd;
	private String BizStsCd;
	
	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getTrxFinishTm() {
		return TrxFinishTm;
	}

	public void setTrxFinishTm(String trxFinishTm) {
		TrxFinishTm = trxFinishTm;
	}

	public float getTrxAmt() {
		return TrxAmt;
	}

	public void setTrxAmt(float trxAmt) {
		TrxAmt = trxAmt;
	}



	public String getSysRtnCd() {
		return SysRtnCd;
	}

	public void setSysRtnCd(String sysRtnCd) {
		SysRtnCd = sysRtnCd;
	}



	public String getBizStsCd() {
		return BizStsCd;
	}

	public void setBizStsCd(String bizStsCd) {
		BizStsCd = bizStsCd;
	}

	public String  getReturnModel() {
		
		String backString = "(\""+TrxId+"\",\""+TrxFinishTm+"\","+TrxAmt+",\""+SysRtnCd+"\",\""+BizStsCd+"\")";
		return backString;
	}
	
	
	public void setReturnModel(Element rootElement) {
		Element elementSys = rootElement.element("MsgBody").element("SysRtnInf").element("SysRtnCd");
		Element elementBiz = rootElement.element("MsgBody").element("BizInf");
		String sysRtnCd =elementSys.getText();
		String TrxId = elementBiz.elementText("TrxId");
		String BizStsCd = elementBiz.elementText("BizStsCd");
		String  trxAmtString = elementBiz.elementText("TrxAmt");
		float TrxAmt = Float.parseFloat(trxAmtString.substring(3));
		
		String TrxFinishTm= elementBiz.elementText("TrxFinishTm");
		TrxFinishTm = TrxFinishTm.replace("T"," ");
		
		this.SysRtnCd = sysRtnCd;
		this.TrxAmt = TrxAmt;
		this.BizStsCd = BizStsCd;
		this.TrxFinishTm = TrxFinishTm;
		this.TrxId= TrxId;
	}

	
}
