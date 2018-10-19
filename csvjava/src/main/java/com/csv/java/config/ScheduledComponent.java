package com.csv.java.config;

import com.csv.java.OnlineDataService.OrderDataService;
import com.csv.java.entity.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    @Scheduled(cron = "0 10/15 * * * ? ")
    public void GenenateOrderDataFromMagento(){
        orderDataService.generateOrderDataFromMagento(10);
    }
}
