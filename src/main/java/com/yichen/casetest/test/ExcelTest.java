package com.yichen.casetest.test;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/14 8:45
 * @describe  测试 excel 写入文件
 */
public class ExcelTest {

    public static void main(String[] args) throws Exception{
//        writeExcel("F:\\data\\test.xlsx");
    }

    public static void writeExcel(String fileName) throws Exception {
        //创建一个workbook对应一个excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        //在workbook中创建一个sheet
    XSSFSheet sheet = workbook.createSheet("Sheet3");

//        XSSFSheet sheet = workbook.getSheet("Sheet3");

        XSSFRow row1 = sheet.getRow(0);

        System.out.println(row1);
        int rownumber = sheet.getLastRowNum();

        XSSFRow row = sheet.createRow(rownumber+1);

        Map<Object,List<Object>> memberMap2 = new HashMap<>();
        List<Object> data = new ArrayList<>();
        data.add("孙悟空");
        data.add("猪八戒");
        data.add("沙和尚");
        data.add("鲁智深");
        data.add("乖，摸摸头");
        data.add("海底两万里");
        data.add("社会心理学");
        data.add("大冰");
        memberMap2.put("name",data);

        for (Object key:memberMap2.keySet()){
            row.createCell(0).setCellValue((String) key);
            List<Object> aa=(List<Object>) memberMap2.get(key);
            for (int j = 1; j <7; j++) {
                row.createCell(j).setCellValue((String) aa.get(j));
            }

        }
//        //设置表头
//        for (int i = 0; i < 10; i++) {
//            row.createCell(i).setCellValue(sheetHead[i]);
//        }
        //写入文件
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            workbook.write(file);
            workbook.close();
//            return true;
        } catch (IOException e) {
            e.printStackTrace();
//            return false;
        }
    }

}
