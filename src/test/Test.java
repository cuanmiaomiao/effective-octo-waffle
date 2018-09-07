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
	static String  ParaFilePath = "./Parameter.xml";//�����ļ�·��

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
		model.setFromReturn(returnModel);//�ӻ�ִ����д��ģ��
		model.setFromApplication(application);//�����뱨��д��ģ��
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
