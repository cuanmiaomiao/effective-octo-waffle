package method;


import java.util.ArrayList;

public class XmlStringDeal {

	
	public static ArrayList<String> XmlStringdistin(String fileString) {
		ArrayList<String> xmlList = new ArrayList<String>();
		
		Integer xmlStart = 0;
		Integer xmlEnd = 0;
		String compXML = null;
		String nextFileString = fileString;

		for (; xmlStart != -1;) {
			xmlStart = nextFileString.indexOf("<?xml");
	        xmlEnd = nextFileString.indexOf("</root>");
	        if (xmlStart == -1 || xmlEnd == -1) {
				break;
			}
	        compXML = nextFileString.substring(xmlStart, xmlEnd+7);
	        nextFileString = nextFileString.substring(xmlEnd+7);
	        xmlList.add(compXML);
		        
		}
		
		return xmlList;
		
	}
}
