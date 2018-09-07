package test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import org.dom4j.*;

import method.*;
import model.*;

public class PHPTest {

	public String phpTest(String in){
		System.out.println("~");
		
        return "Hello World"+in;
       
    }
/*
	public void TimeTask() {
		Timer timer = new Timer();
		timer.schedule(new Task(), 0, 5 * 1000);
		
	}*/

	public void readAll() {
		
		/**��ȡ�����ļ�
		 */
		Parameter parameter = new Parameter();
		parameter.setParam(Test.ParaFilePath);
		
		/**��ȡlog�ļ���fileString
		 */
		Log log = new Log();
        String fileString = null;
        fileString = log.readPartFile(parameter);
        
        /**
         * Ѱ�����һ��</root>λ��end
         * ��readMark+end��Ϊ��һ�ε�readmarkд�������ļ�
         */
        parameter.logReadMarkUpdate(fileString.lastIndexOf("</root>"), Test.ParaFilePath);

        /**
         *   �и�fileString���õ�����xml��ʽString�ַ���
         */
        ArrayList<String> xmlList = XmlStringDeal.XmlStringdistin(fileString);
        
		for (int i = 0; i < xmlList.size(); i++) {
	        Element lockRoot =Xml2Model.readxmlfromstring(xmlList.get(i)); 
	        String lockId = Test.getTrxId(lockRoot);
			for (int j = i+1; j < xmlList.size(); j++) {	
		        Element searchRoot =Xml2Model.readxmlfromstring(xmlList.get(j)); 			
		 		String SearchId = Test.getTrxId(searchRoot);
					if (lockId.equals(SearchId)) {
						Test.modelset(lockRoot, searchRoot, parameter);					
						xmlList.remove(i);
						xmlList.remove(j-1);
						i--;
						break;
					}
					}
				}
		
    	/**��ȡxml�洢Ŀ¼�µ������ļ��������ϴ�δƥ��ɹ����ļ���
    	 * �õ�����ͻ�ִ����ģ�Ͷ���
    	 */
		ArrayList<String> xmlName = new ArrayList<String>();
		xmlName = Xml2Model.getFilesNames(parameter.getXmlSavePath());
		for (int i = 0; i < xmlName.size(); i++) {
    		Document xmlLock = Xml2Model.reader(parameter.getXmlSavePath()+xmlName.get(i));
    		Element lockRoot = xmlLock.getRootElement();
	        String lockId = Test.getTrxId(lockRoot);
			for (int j =0; j < xmlList.size(); j++) {	
				Element searchRoot = Xml2Model.readxmlfromstring(xmlList.get(j)); 
		 		String SearchId = Test.getTrxId(searchRoot);
					if (lockId.equals(SearchId)) {
						Test.modelset(lockRoot, searchRoot, parameter);						
						Xml2Model.deleteFile(parameter.getXmlSavePath()+xmlName.get(i));
						xmlList.remove(j);
						xmlName = Xml2Model.getFilesNames(parameter.getXmlSavePath());
						i--;
						break;
					}
			}
		}
		
		/**
		 *   д��xml�ļ���ָ��Ŀ¼
		 *   ��ϵͳʱ�������Ա������Ƴ�ͻ
		 */
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeString = time.format(new Date());
		for (int i = 0; i < xmlList.size(); i++) {
			String xml = xmlList.get(i);
			String xmlNameString = timeString+"xml"+i;
		    Xml2Model.writeCharFromFile(parameter.getXmlSavePath(),xmlNameString, xml);
		}
		

    	/**��ģ�Ͷ�����ÿ��ģ����Ϊһ������д�����ݿ���
    	*/
    	ArrayList<String> returnsql = new ArrayList<String>();
    	for (int i = 0; i < Test.returnModels.size(); i++) {
	      String sql ="INSERT INTO "+parameter.getReturnTableName()+" VALUES"+Test.returnModels.get(i).getReturnModel()+";";
	      System.out.println(sql);
	      returnsql.add(sql);
		}
    	DataBaseAndTest.insertTest(returnsql);
    	
    	ArrayList<String> applicationsql = new ArrayList<String>();
    	for (int i = 0; i < Test.applicationModels.size(); i++) {
			String sql ="INSERT INTO "+parameter.getApplicationTableName()+" VALUES"+Test.applicationModels.get(i).getApplicationModel()+";";
			System.out.println(sql);
			applicationsql.add(sql);
		}
    	DataBaseAndTest.insertTest(applicationsql);
    	
    	ArrayList<String> modelsql = new ArrayList<String>();
    	for (int i = 0; i < Test.models.size(); i++) {
    		String sql ="INSERT INTO "+parameter.getModelTableName()+" VALUES"+Test.models.get(i).getModel()+";";
    		System.out.println(sql);
    		modelsql.add(sql);
		}
    	DataBaseAndTest.insertTest(modelsql);
	}
}
