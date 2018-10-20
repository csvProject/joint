package com.csv.java.config;

import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.common.tool.DateUtil;
import com.csv.java.common.tool.UUIDUtil;
import com.csv.java.entity.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.plugin.util.UIUtil;

import java.util.*;

import static com.csv.java.common.tool.DeleteFileUtil.deleteFile;
import static com.csv.java.config.ConstantConfig.DELETE_ZIP_FILE_DAY;

@Component
public class ScheduledComponent {
    public static List<FileDto> ZIP_FILES = new ArrayList<>();

    @Autowired
    private OrderDataService orderDataService;

    @Scheduled(cron = "0 0 2 ? * 1")  //表示每个星期一凌晨2点
    public void pushDataScheduled() {
        Iterator<FileDto> it = ZIP_FILES.iterator();
        while(it.hasNext()){
            FileDto file = it.next();
            long from = file.getCreateTime();
            long to = new Date().getTime();
            int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
            if(days>=DELETE_ZIP_FILE_DAY){
                deleteFile(file.getFileName());
                it.remove();
            }
        }
    }

    /*
    每整点15分+10分钟进行同步
    0 10/30 * * * ?  每整点30+10分钟进行同步
     */
    @Scheduled(cron = "0 10/15 * * * ?")
    public void GenenateOrderDataFromMagento(){
        orderDataService.generateOrderDataFromMagento(10);
    }

    /*
    同步并发测试，每2秒执行
     */
    /*@Scheduled(cron = "0/4 * * * * ?")
    public void test(){
        System.out.println("测试开始:" + DateUtil.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("UUID:" + UUIDUtil.getUUID());
        try
        {
            Thread.currentThread().sleep(10000);//毫秒
        }
        catch(Exception e){}
        *//*Timer timer=new Timer();//实例化Timer类

        timer.schedule(new TimerTask(){
                    public void run(){
                        System.out.println("退出");
                        this.cancel();
                    }
                },4000);//等待4秒*//*
        System.out.println("我等了4秒:" + DateUtil.date2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }*/
}
