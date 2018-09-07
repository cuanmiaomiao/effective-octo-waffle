package model;

import java.io.*;
import org.dom4j.*;



public class Log {
		private String path;
		private Integer length;
		
		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
		
		public Integer getLength() {
			return length;
		}

		public void setLength(Integer length) {
			this.length = length;
		}
		/**
		 * 
		 * @param srcFilePath
		 * @return
		 */
		public String readLog( String srcFilePath /* , String length , String startMark*/) {
			InputStream is = null;
	        InputStreamReader isr = null;
	        BufferedReader br = null;
	        String fileString = null;
	            try {
					is = new FileInputStream(srcFilePath);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					isr = new InputStreamReader(is, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            br = new BufferedReader(isr);
	            String line;
	            try {
					for (line = br.readLine(); line != null; line = br.readLine()) {
						//System.out.println("line=" + line);
						fileString=fileString+line;
					}
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return fileString;
		}
		
		
		public String readPartFile(Parameter parameter) {
	            String content;
	            File file = new File(parameter.getLogPath());
	            FileReader fr = null;
				try {
					fr = new FileReader(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            try {
					fr.skip(parameter.getLogReadMark() - 1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  // 输入流跳过若干字符
	            char[] buffer = new char[parameter.getLogReadLength()];
	            try {
					fr.read(buffer);
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            content = new String(buffer);
	            return content; 
	      }
		
		
		public static boolean distinguish(Element rootElm) {
			String Drctn = rootElm.element("MsgHeader").elementText("Drctn");
			//区分申请和回执报文
				if (Drctn.equals("22")) {
					return true;
				} else{
					return false;
				}
		}


	}
		
