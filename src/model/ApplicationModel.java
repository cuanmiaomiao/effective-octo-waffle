package model;

import java.util.ArrayList;

import org.dom4j.Element;

public class ApplicationModel {
	
	private String PyeeAcctIssrId; 
	private String TrxId;
	private String TrxDtTm; 
	private float TrxAmt;
	
	private String pyeeIdName;
	
	
	public String getPyeeAcctIssrId() {
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
	
	public String getPyeeId( ArrayList<String> Pyee,ArrayList<String> PyeeName) {
		for (int i = 0; i < Pyee.size(); i++) {
			if (PyeeAcctIssrId.equals(Pyee.get(i))) {
				return PyeeName.get(i);
			}	
		}
		return "其他";
/*		if (runnableList.get(which) == null) return;
		runnableList.get(which).run();
		*//*
		switch (pyeemark) {
		case 0:
			return PyeeName.get(0);
		case 1:
			return PyeeName.get(1);
		case 2:
			return PyeeName.get(2);
		default:
			return "其他";
		}*/
	}
	

	public void setApplicationModel(Element rootElement) {
		Element elementPyee = rootElement.element("MsgBody").element("PyeeInf");
		Element elementTrx = rootElement.element("MsgBody").element("TrxInf");
		
		String pyeeAcctIssrId= elementPyee.elementText("PyeeAcctIssrId");
		
		String TrxId = elementTrx.elementText("TrxId");
		String TrxDtTm = elementTrx.elementText("TrxDtTm");
		TrxDtTm = TrxDtTm.replace("T", " ");
		String  trxAmtString = elementTrx.elementText("TrxAmt");
		float TrxAmt = Float.parseFloat(trxAmtString.substring(3));
		
		this.PyeeAcctIssrId = pyeeAcctIssrId;
		this.TrxAmt = TrxAmt;
		this.TrxDtTm = TrxDtTm;
		this.TrxId= TrxId;
	}

	
	
	public String  getApplicationModel() {
		
		String backString = "(\""+TrxId+"\",\""+TrxDtTm+"\",\""+pyeeIdName+"\","+TrxAmt+")";
		return backString;
	}
	public String getPyeeIdName() {
		return pyeeIdName;
	}
	public void setPyeeIdName(String pyeeIdName) {
		this.pyeeIdName = pyeeIdName;
	}

}
