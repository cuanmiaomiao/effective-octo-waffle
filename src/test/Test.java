package test;

import java.text.SimpleDateFormat;
import java.util.*;

import method.*;
import model.*;

import org.dom4j.*;



public class Test {
	static ArrayList<Model> models = new ArrayList<Model>();
	static ArrayList<ReturnModel> returnModels = new ArrayList<ReturnModel>();
	static ArrayList<ApplicationModel> applicationModels = new ArrayList<ApplicationModel>();
	static String  ParaFilePath = "./Parameter.xml";//配置文件路径

	public static void modelset(Element lockRoot,Element searchRoot,Parameter parameter) {
		ApplicationModel application = new ApplicationModel();
		ReturnModel returnModel = new ReturnModel();
		if (Log.distinguish(lockRoot)) {
			application.setApplicationModel(lockRoot);
			application.setPyeeIdName(application.getPyeeId(parameter.getPyeeId(),parameter.getPyeeName()));
			returnModel.setReturnModel(searchRoot);
		}else {
			application.setApplicationModel(searchRoot);
			application.setPyeeIdName(application.getPyeeId(parameter.getPyeeId(),parameter.getPyeeName()));
			returnModel.setReturnModel(lockRoot);	
		}
		applicationModels.add(application);
		returnModels.add(returnModel);
		
    	Model model = new Model();
		model.setFromReturn(returnModel);//从回执报文写入模型
		model.setFromApplication(application);//从申请报文写入模型
		models.add(model);
	}
	
	public static String getTrxId(Element root) {
		String Id;
		if (Log.distinguish(root)) {
			Id =root.element("MsgBody").element("TrxInf").elementText("TrxId");
		}else {
	        Id =root.element("MsgBody").element("BizInf").elementText("TrxId");
		}
        return Id;
	}
	
}
