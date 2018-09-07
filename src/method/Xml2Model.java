package method;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class Xml2Model {
	
	

	public static Document reader(String filename) {  
	    Document document = null;  
	    try {  
	        SAXReader saxReader = new SAXReader();  
	        document = saxReader.read(new File(filename)); // 读取XML文件,获得document对象  
	    } catch (Exception ex) {
	        ex.printStackTrace();  
	    }  
	    return document;  
	}

	public static Element readxmlfromstring(String xml) {
		Document xmlDocument = null;
		try {
			xmlDocument = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Element root = xmlDocument.getRootElement(); 
		return root;
	}
	
	
	public static ArrayList<String> getFilesNames(String path) {
	    ArrayList<String> files = new ArrayList<String>();
	    File file = new File(path);
	    File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String fileName = tempList[i].getName();
                files.add(fileName);
            }
        }
	    return files;
	}
	
	public static void writeCharFromFile(String savepath , String xmlName, String compXML) {
		
		         byte[] byteArray= compXML.getBytes();
		        File file= new File(savepath+xmlName+".xml");
		        OutputStream os = null;
				try {
					os = new FileOutputStream( file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		         try {
					os.write( byteArray);
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}  

	public static void doc2XmlFile(Document document, String filename) {  
	    try {  
	        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
	                new FileOutputStream(filename), "UTF-8"));  
	        writer.write(document);  
	        writer.close();  
	    } catch (Exception ex) {  
	        ex.printStackTrace();  
	    }  
	    } 
	

    public static void deleteFile(String filePath) {// 删除单个文件
        File file;
        file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();// 文件删除
        }
    } 
}
