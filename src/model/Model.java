package model;


public class Model {

	private String TrxId;
	
	private float TrxAmt;
	private String PyeeAcctIssrId; 
	private String pyeeIdName;
	
	private String TrxDtTm; 
	private String TrxFinishTm;
	
	private String SysRtnCd;
	private String BizStsCd;
	
	
	public String getPyeeNm() {
		return PyeeAcctIssrId;
	}

	public void setPyeeNm(String pyeeAcctIssrId) {
		PyeeAcctIssrId = pyeeAcctIssrId;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getTrxDtTm() {
		return TrxDtTm;
	}

	public void setTrxDtTm(String trxDtTm) {
		TrxDtTm = trxDtTm;
	}

	public float getTrxAmt() {
		return TrxAmt;
	}

	public void setTrxAmt(float trxAmt) {
		TrxAmt = trxAmt;
	}

	public String getTrxFinishTm() {
		return TrxFinishTm;
	}

	public void setTrxFinishTm(String trxFinishTm) {
		TrxFinishTm = trxFinishTm;
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

	public void setFromReturn( ReturnModel returnModel) {
		TrxId = returnModel.getTrxId();
		TrxFinishTm = returnModel.getTrxFinishTm(); 
		TrxAmt = returnModel.getTrxAmt();
		SysRtnCd = returnModel.getSysRtnCd();
		BizStsCd = returnModel.getBizStsCd();
	}
	
	
	public void setFromApplication( ApplicationModel applicationModel) {
		PyeeAcctIssrId = applicationModel.getPyeeAcctIssrId(); 
		TrxId = applicationModel.getTrxId();
		TrxDtTm = applicationModel.getTrxDtTm(); 
		TrxAmt = applicationModel.getTrxAmt();
		pyeeIdName = applicationModel.getPyeeIdName();
		
	}
	
	public String  getModel() {
		
		String backString = "(\""+TrxId+"\",\""+TrxFinishTm+"\",\""+pyeeIdName+"\","+TrxAmt+",\""+SysRtnCd+"\",\""+BizStsCd+"\")";
		return backString;
	}

	public String getPyeeIdName() {
		return pyeeIdName;
	}

	public void setPyeeIdName(String pyeeIdName) {
		this.pyeeIdName = pyeeIdName;
	}
}
