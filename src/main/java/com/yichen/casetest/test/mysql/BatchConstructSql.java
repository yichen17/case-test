package com.yichen.casetest.test.mysql;

import com.yichen.casetest.utils.StringUtils;
import com.yichen.casetest.utils.TimeUtils;

import java.io.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/17 19:14
 * @describe 批量构造mysql sql插入语句
 * https://blog.csdn.net/qq_26897321/article/details/120886520
 */
public class BatchConstructSql {

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        System.out.println("start time " + startTime);
        int[] appStatus = {-3,-1,0,3,6,5,2,10,12,20,31,5,5,5,5};
        int[] repayStatus = {1,2};

        String path = "D:\\personal-project-list\\case-test\\src\\main\\resources\\data\\";
        String sql = "INSERT INTO `mysql-research`.`t_index_query_test`(`create_time`,`app_status`, `repay_status`, `remark`, `name`, `age`) " +
                "VALUES ('%s', %d, %d, '%s', '%s', %d);";
//        System.out.println(String.format(sql, "2022-01-01 00:00:00", 5 ,2, "test batch", "yichen", 18));
        String timePrefix = "2020-";
        String month;
        long startDate,endDate,internalTime;
        StringBuilder builder = new StringBuilder();
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path)) ;
        BufferedWriter bos = new BufferedWriter(new FileWriter(new File(path,"insert-data.sql"),true));
        for (int i=1; i<13; i++){
            if (i < 10){
                month = "0"+i;
            }
            else {
                month = i + "";
            }
            startDate = TimeUtils.convertDateToTimeStamp(timePrefix + month + "-01 00:00:00", null);
            endDate = TimeUtils.convertDateToTimeStamp(TimeUtils.getLastDayOfMonth(2020,i) + " 23:59:59", null);
            internalTime = endDate - startDate;
            // 月份
            for (int k = 0; k<300; k++){
                for (int j=0; j<10000; j++){
                    String createTime = TimeUtils.timeStampToDate(String.valueOf((long)(Math.random() * internalTime) + startDate) , "yyyy-MM-dd HH:mm:ss");
                    int aps = appStatus[(int)(Math.random() * appStatus.length)];
                    int rps = repayStatus[(int)(Math.random() * repayStatus.length)];
                    String remark = "i = " + i + ", pos = " + 10000*(k-1)+j;
                    int age = (int)(Math.random() * 100);
                    String name = StringUtils.randomString(6);
                    builder.append(String.format(sql,createTime,aps,rps,remark,name,age)).append("\n");
                }
                bos.append(builder.toString());
                bos.flush();
                builder.setLength(0);
            }
        }
        bos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("end time " + endTime + " 执行时长 " + (endTime -startTime)/1000 );
    }
}
