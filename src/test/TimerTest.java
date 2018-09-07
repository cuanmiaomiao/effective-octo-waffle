package test;

import java.util.Timer;
import java.util.TimerTask;

import model.Log;
import model.Parameter;

public class TimerTest {

public static void main(String[] args) {
	
	/**读取配置文件
	 */
	Parameter parameter = new Parameter();
	parameter.setParam(Test.ParaFilePath);
	
	/**读取log文件到fileString
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
                System.out.println("定时器停止了");
                timer.cancel();// 停止定时器
            }
        }
    };
    timer.schedule(task, 0, 1000);// 1秒一次
}
}
