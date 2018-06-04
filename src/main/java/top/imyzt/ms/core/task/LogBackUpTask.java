package top.imyzt.ms.core.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
/**
 * <p>
 * 日志定时备份 >>>> 定时任务
 * </p>
 *
 * Scheduled.fixedRate,每隔多少毫秒执行一次<br/>
 * Scheduled.cron,由cron表达式决定.<br/>
 * cron表达式详情：https://www.cnblogs.com/javahr/p/8318728.html
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
@Component
//开启定时任务的注解
@EnableScheduling
public class LogBackUpTask {


    /*@Scheduled(fixedRate = 5000)*/
    @Scheduled(cron = "0 0 1 * * ?")    //每天凌晨 1点备份一次
    public void LogBackUpTask(){

        /**
         * 具体业务自定义
         * 以日志备份模拟操作
         *
         * 此处仅做测试使用.无法满足实际需求
         */

        String path = Class.class.getResource("/").getPath().substring(1);

        int index = path.lastIndexOf("/", path.lastIndexOf("/", path.lastIndexOf("/") - 1) - 1);

        File file = new File(path.substring(0, index + 1) + File.separator + "log");
        Log log = LogFactory.get();

        if (file.exists()){

            //假如备份地址是 D:/backup
            String backup = "D:\\backup\\";

            Runtime runtime = Runtime.getRuntime();
            try {
                String today = DateUtil.today();
                File f = new File(backup, today);
                String exec = "xcopy " + file.getAbsolutePath() + " " + f.getAbsolutePath() + "\\ /s";
                System.out.println(exec);
                runtime.exec(exec);

            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }

        }

        log.info("日志备份完毕");
    }

}
