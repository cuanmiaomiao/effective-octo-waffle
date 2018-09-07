package test;

import java.util.Timer;
import java.util.TimerTask;

import model.Log;
import model.Parameter;

public class TimerTest {

public static void main(String[] args) {
	
	/**��ȡ�����ļ�
	 */
	Parameter parameter = new Parameter();
	parameter.setParam(Test.ParaFilePath);
	
	/**��ȡlog�ļ���fileString
	 */
	Log log = new Log();
	final int readTime = log.readLog(parameter.getLogPath()).length()/parameter.getLogReadLength() +1;
    
	final Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        private int count;

        @Override
        public void run() {
            this.count++;
            
            System.out.println(count);
            if (count >= readTime) {
                System.out.println("��ʱ��ֹͣ��");
                timer.cancel();// ֹͣ��ʱ��
            }
        }
    };
    timer.schedule(task, 0, 1000);// 1��һ��
}
}
