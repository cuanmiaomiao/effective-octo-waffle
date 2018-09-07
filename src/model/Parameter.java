package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import method.Xml2Model;

import org.dom4j.*;


public class Parameter {

	private String logPath;//报文路径
	private Integer logReadMark;
	private Integer logReadLength;
	
	private String  xmlSavePath;//中间xml文档存储路径
	
	private ArrayList<String> pyeeId = new ArrayList<String>();//顺序录入各三方支付机构代码
	private ArrayList<String> pyeeName = new ArrayList<String>();//顺序录入各三方支付机构名称			

	
	private String returnTableName;
	private String applicationTableName;
	private String modelTableName;
	private String DB_URL;
	private String DB_USER;
	private String DB_PASS;
	
	private Document param;

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public Integer getLogReadMark() {
		return logReadMark;
	}

	public void setLogReadMark(Integer logReadMark) {
		this.logReadMark = logReadMark;
	}

	public Integer getLogReadLength() {
		return logReadLength;
	}

	public void setLogReadLength(Integer logReadLength) {
		this.logReadLength = logReadLength;
	}

	public String getXmlSavePath() {
		return xmlSavePath;
	}

	public void setXmlSavePath(String xmlSavePath) {
		this.xmlSavePath = xmlSavePath;
	}

	public ArrayList<String> getPyeeId() {
		return pyeeId;
	}

	public void setPyeeId(ArrayList<String> pyeeId) {
		this.pyeeId = pyeeId;
	}

	public ArrayList<String> getPyeeName() {
		return pyeeName;
	}

	public void setPyeeName(ArrayList<String> pyeeName) {
		this.pyeeName = pyeeName;
	}

	public String getReturnTableName() {
		return returnTableName;
	}

	public void setReturnTableName(String returnTableName) {
		this.returnTableName = returnTableName;
	}

	public String getApplicationTableName() {
		return applicationTableName;
	}

	public void setApplicationTableName(String applicationTableName) {
		this.applicationTableName = applicationTableName;
	}

	public String getModelTableName() {
		return modelTableName;
	}

	public void setModelTableName(String modelTableName) {
		this.modelTableName = modelTableName;
	}

	public String getDB_URL() {
		return DB_URL;
	}

	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}

	public String getDB_USER() {
		return DB_USER;
	}

	public void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}

	public String getDB_PASS() {
		return DB_PASS;
	}

	public void setDB_PASS(String dB_PASS) {
		DB_PASS = dB_PASS;
	}

	public Document getParam() {
		return param;
	}

	public void setParam(Document param) {
		this.param = param;
	}
	
	
	
	public void setParam(String paramPath) {
		this.param = Xml2Model.reader(paramPath);
		Element rootElement = param.getRootElement();
		logPath = rootElement.element("LogInfor").elementText("logPath");
		logReadMark = Integer.parseInt(rootElement.element("LogInfor").elementText("logReadMark"));
		logReadLength = Integer.parseInt(rootElement.element("LogInfor").elementText("logReadLength"));
		xmlSavePath = rootElement.elementText("XMLInfor");

		returnTableName = rootElement.element("DBInfor").elementText("ReturnTable");
		applicationTableName = rootElement.element("DBInfor").elementText("ApplicationTable");
		modelTableName = rootElement.element("DBInfor").elementText("ModelTable");
		DB_URL = rootElement.element("DBInfor").elementText("DB_URL>");
		DB_USER = rootElement.element("DBInfor").elementText("DB_USER");
		DB_PASS = rootElement.element("DBInfor").elementText("DB_PASS");
		
		List nodes = rootElement.element("PyeeContrast").element("PyeeId").elements("ID");      
		for (Iterator it = nodes.iterator(); it.hasNext();) {      
		    Element elm = (Element) it.next();
		    pyeeId.add(elm.getText());  
		}  	
		nodes = rootElement.element("PyeeContrast").element("PyeeName").elements("Name");      
		for (Iterator it = nodes.iterator(); it.hasNext();) {      
		    Element elm = (Element) it.next();      
		    pyeeName.add(elm.getText());  
		}
		
	}
	
	public void logReadMarkUpdate( Integer Mark, String paramPath) {
		Element root = param.getRootElement();
		String markString;
		if (Mark != -1) {
			markString = String.valueOf(logReadMark+Mark+7);
			root.element("LogInfor").element("logReadMark").setText(markString);
			Xml2Model.doc2XmlFile(param, paramPath);
		}
	}
	
}
